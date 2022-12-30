package com.cczu.examples.hdfs.namenode.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest;
import com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse;
import com.cczu.examples.hdfs.rpc.model.HeartbeatRequest;
import com.cczu.examples.hdfs.rpc.model.HeartbeatResponse;
import com.cczu.examples.hdfs.rpc.model.MkdirRequest;
import com.cczu.examples.hdfs.rpc.model.MkdirResponse;
import com.cczu.examples.hdfs.rpc.model.RegisterRequest;
import com.cczu.examples.hdfs.rpc.model.RegisterResponse;
import com.cczu.examples.hdfs.rpc.model.ShutdownRequest;
import com.cczu.examples.hdfs.rpc.model.ShutdownResponse;
import com.cczu.examples.hdfs.rpc.service.NameNodeServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * namenode server
 *
 * @author yjz
 * @date 2021/12/30
 */
public class NameNodeServiceImpl extends NameNodeServiceGrpc.NameNodeServiceImplBase {

    private static final Integer STATUS_SUCCESS = 1;
    private static final Integer STATUS_FAILURE = 2;
    private static final Integer STATUS_SHUTDOWN = 3;
    /**
     * 每次拉取的数量
     */
    private static final Integer BACKUP_NODE_FETCH_SIZE = 10;
    private volatile Boolean isRunning = true;
    /**
     * 当前内存里缓冲了哪个磁盘文件
     */
    private String bufferedFlushedTxid = null;
    /**
     * 当前缓冲的一小部分editsLog数据
     */
    private JSONArray currentBufferedEditsLog = new JSONArray();
    /**
     * 负责管理元数据的核心组件
     */
    private FSNameSystem nameSystem;
    /**
     * 管理集群中所有的datanode
     */
    private DataNodeManager datanodeManager;
    /**
     * 当前backupNode节点同步到了那一条txid
     */
    private Long syncedTxid = 0L;
    /**
     * 从当前已经在内存里缓存的数据中拉取的editsLog
     */
    private long currentBufferedMaxTxid = 0L;


    public NameNodeServiceImpl(FSNameSystem nameSystem, DataNodeManager datanodeManager) {
        this.nameSystem = nameSystem;
        this.datanodeManager = datanodeManager;
    }


    /**
     * 启动监听
     */
    public void start() {
        System.out.println("开启监听指定的rpc server的端口号，来接受这个请求");
    }

    /**
     * datanode 进行注册
     */
    @Override
    public void register(RegisterRequest request, StreamObserver<RegisterResponse> responseObserver) {
        datanodeManager.register(request.getIp(), request.getHostname());
        RegisterResponse response = RegisterResponse.newBuilder().setStatus(STATUS_SUCCESS).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * 心跳注册
     */
    @Override
    public void heartbeat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        datanodeManager.heartbeat(request.getIp(), request.getHostname());
        HeartbeatResponse response = HeartbeatResponse.newBuilder().setStatus(STATUS_SUCCESS).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void mkdir(MkdirRequest request, StreamObserver<MkdirResponse> responseObserver) {
        try {
            MkdirResponse response = null;
            if (!isRunning) {
                response = MkdirResponse.newBuilder().setStatus(STATUS_SHUTDOWN).build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                this.nameSystem.mkdir(request.getPath());
                System.out.println("创建目录,path=" + request.getPath());
                response = MkdirResponse.newBuilder().setStatus(STATUS_SUCCESS).build();
            }
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown(ShutdownRequest request, StreamObserver<ShutdownResponse> responseObserver) {
        this.isRunning = false;
        this.nameSystem.flush();
    }

    @Override
    public void fetchEditsLog(FetchEditsLogRequest request, StreamObserver<FetchEditsLogResponse> responseObserver) {
        FetchEditsLogResponse response;
        FSEditlog fsEditLog = nameSystem.getFsEditLog();
        List<String> flushedTxids = fsEditLog.getFlushedTxids();
        JSONArray fetchedJsonArray = new JSONArray();
        // 1.如果此时还没有刷出来任何磁盘文件的话，那么数据仅仅存放在内存缓冲里
        if (flushedTxids.size() == 0) {
            System.out.println("暂时没有任何磁盘文件，直接从内存缓冲中拉取");
            fetchFromBuffer(fetchedJsonArray);
        }
        // 2. 如果此时有落地磁盘的文件了，这个时候就要扫描所有磁盘文件的索引范围
        else {
            // 如果有磁盘文件，而且内存里还缓存了某个磁盘文件的数据
            if (bufferedFlushedTxid != null) {
                // 如果要拉取的数据就在当前缓存的文件中
                if (existInFlushedFile(bufferedFlushedTxid)) {
                    System.out.println("尝试从磁盘文件中拉取，flushTxid=" + bufferedFlushedTxid);
                    fetchFromCurrentBuffer(fetchedJsonArray);
                }
                // 如果要拉取的数据不再当前缓存的文件中，需要从下一个磁盘文件拉取
                else {
                    String nextFlushedTxid = getNextFlushedTxid(flushedTxids, bufferedFlushedTxid);
                    // 如果可以找到下一个磁盘文件，就从下一个磁盘文件读取数据
                    if (nextFlushedTxid != null) {
                        fetchFromFlushedFile(nextFlushedTxid, fetchedJsonArray);
                    }
                    // 如果没有找到下一个磁盘文件，则需要从内存中读取
                    else {
                        fetchFromBuffer(fetchedJsonArray);
                    }
                }
            } else {
                boolean fetchedFromFlushFile = false;
                //遍历所有磁盘文件的索引范围
                for (String flushTxid : flushedTxids) {
                    // 如果要拉取的数据在某个磁盘文件中
                    if (existInFlushedFile(flushTxid)) {
                        fetchFromFlushedFile(flushTxid, fetchedJsonArray);
                        fetchedFromFlushFile = true;
                        break;
                    }
                }
                // 第二种，你要拉取的txid已经比磁盘文件里的全部都新了，还在内存缓冲里
                if (!fetchedFromFlushFile) {
                    System.out.println("所有磁盘文件都没有找到拉取的editslog，直接从内存缓冲中拉取");
                    fetchFromBuffer(fetchedJsonArray);
                }
            }
        }
        response = FetchEditsLogResponse.newBuilder()
                .setEditsLog(fetchedJsonArray.toJSONString())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    /**
     * 获取下一个磁盘文件的范围
     *
     * @param flushedTxids
     * @param bufferedFlushedTxid
     * @return
     */
    private String getNextFlushedTxid(List<String> flushedTxids, String bufferedFlushedTxid) {
        // 读取下一个磁盘文件数据
        String nextFlushedTxid = null;
        for (int i = 0; i < flushedTxids.size(); i++) {
            if (flushedTxids.get(i).equals(bufferedFlushedTxid)) {
                if (i + 1 < flushedTxids.size()) {
                    nextFlushedTxid = flushedTxids.get(i + 1);
                }
            }
        }
        return nextFlushedTxid;
    }

    /**
     * 从内存缓冲中的editslog拉取数据
     *
     * @param fetchedJsonArray
     */
    private void fetchFromBuffer(JSONArray fetchedJsonArray) {
        // 如果要拉取的txid还在上一次内存缓冲中，此时继续从内存缓存中拉取
        // 减少锁的争用
        long fetchTxid = syncedTxid + 1;
        if (fetchTxid <= currentBufferedMaxTxid) {
            System.out.println("上一次内存缓冲的文件可以继续使用...");
            fetchFromCurrentBuffer(fetchedJsonArray);
            return;
        }
        currentBufferedEditsLog.clear();
        String[] bufferedEditsLog = this.nameSystem.getFsEditLog().getBufferedEditsLog();
        for (String editLog : bufferedEditsLog) {
            currentBufferedEditsLog.add(JSON.parseObject(editLog));
            // 我们在这里记录一下，当前内存中的数据最大的txid是多少，这样下次再拉取
            // 判断，内存缓冲的数据是否可以读取，不要每次都从内存缓冲中去加载
            currentBufferedMaxTxid = JSON.parseObject(editLog).getLongValue("txid");
        }
        bufferedFlushedTxid = null;

        fetchFromCurrentBuffer(fetchedJsonArray);
    }

    /**
     * 从已经刷入磁盘的文件里读取editslog，同时缓存数据到内存
     *
     * @param flushTxid
     */
    private void fetchFromFlushedFile(String flushTxid, JSONArray fetchedJsonArray) {
        try {
            bufferedFlushedTxid = flushTxid;
            // 此时可以把磁盘文件的数据读取出来，以及下一个磁盘文件的数据都读出来，放到内存缓冲里
            // 就怕拉取的数据不够10条
            currentBufferedEditsLog.clear();
            String[] splitFlushTxid = flushTxid.split("_");
            long startTxid = Long.parseLong(splitFlushTxid[0]);
            long endTxid = Long.parseLong(splitFlushTxid[1]);
            String currentEditsLogFile = "/Users/yjz/IdeaProjects/priv_garden/data/dfs/editsLog/editsLog-" + startTxid + "-" + endTxid + ".log";
            List<String> editLogs = Files.readAllLines(Paths.get(currentEditsLogFile),
                    StandardCharsets.UTF_8);
            for (String editLog : editLogs) {
                currentBufferedEditsLog.add(JSON.parseObject(editLog));
                currentBufferedMaxTxid = JSON.parseObject(editLog).getLongValue("txid");
            }
            fetchFromCurrentBuffer(fetchedJsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从当前已经在内存里缓存的数据中拉取editslog
     *
     * @param fetchedJsonArray
     */
    private void fetchFromCurrentBuffer(JSONArray fetchedJsonArray) {
        int fetchCount = 0;
        for (int i = 0; i < currentBufferedEditsLog.size(); i++) {
            if (currentBufferedEditsLog.getJSONObject(i).getLong("txid") == syncedTxid + 1) {
                fetchedJsonArray.add(currentBufferedEditsLog.getJSONObject(i));
                syncedTxid = currentBufferedEditsLog.getJSONObject(i).getLong("txid");
                fetchCount++;
            }
            if (fetchCount == BACKUP_NODE_FETCH_SIZE) {
                break;
            }
        }
    }

    /**
     * 是否存在于刷到磁盘的索引文件中
     *
     * @param flushedTxid
     * @return
     */
    private Boolean existInFlushedFile(String flushedTxid) {
        String[] splitFlushTxid = flushedTxid.split("_");
        long startTxid = Long.parseLong(splitFlushTxid[0]);
        long endTxid = Long.parseLong(splitFlushTxid[1]);
        long fetchTxid = syncedTxid + 1;
        if (fetchTxid >= startTxid && fetchTxid <= endTxid) {
            return true;
        }
        return false;
    }


}
