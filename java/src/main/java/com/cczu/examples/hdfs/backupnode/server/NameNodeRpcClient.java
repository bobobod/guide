package com.cczu.examples.hdfs.backupnode.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cczu.examples.hdfs.rpc.model.FetchEditsLogRequest;
import com.cczu.examples.hdfs.rpc.model.FetchEditsLogResponse;
import com.cczu.examples.hdfs.rpc.model.MkdirRequest;
import com.cczu.examples.hdfs.rpc.model.MkdirResponse;
import com.cczu.examples.hdfs.rpc.model.ShutdownRequest;
import com.cczu.examples.hdfs.rpc.model.ShutdownResponse;
import com.cczu.examples.hdfs.rpc.service.NameNodeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

/**
 * @author yjz
 * @date 2022/1/10
 */
public class NameNodeRpcClient {
    private static final String NAMENODE_HOSTNAME = "localhost";
    private static final Integer NAMENODE_PORT = 50070;
    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    public NameNodeRpcClient() {
        ManagedChannel channel = NettyChannelBuilder
                .forAddress(NAMENODE_HOSTNAME, NAMENODE_PORT)
                .negotiationType(NegotiationType.PLAINTEXT)
                .build();
        this.namenode = NameNodeServiceGrpc.newBlockingStub(channel);
    }

    /**
     * 创建目录
     *
     * @param path
     * @throws Exception
     */
    public void mkdir(String path) throws Exception {
        MkdirRequest mkdirRequest = MkdirRequest.newBuilder().setPath(path).build();
        MkdirResponse mkdirResponse = namenode.mkdir(mkdirRequest);
        System.out.println("创建目录的响应：" + mkdirResponse.getStatus());
    }

    /**
     * 优雅关闭
     *
     * @throws Exception
     */
    public void shutdown() throws Exception {
        ShutdownRequest shutdownRequest = ShutdownRequest.newBuilder().setCode(1).build();
        ShutdownResponse shutdownResponse = namenode.shutdown(shutdownRequest);
        System.out.println("优雅关闭" + shutdownResponse);
    }

    /**
     * 拉取editslog
     *
     * @return
     */
    public JSONArray fetchEditsLog() {
        FetchEditsLogRequest request = FetchEditsLogRequest.newBuilder().setCode(1).build();
        FetchEditsLogResponse fetchEditsLogResponse = namenode.fetchEditsLog(request);
        String editsLog = fetchEditsLogResponse.getEditsLog();
        return JSON.parseArray(editsLog);
    }
}
