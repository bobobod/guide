package com.cczu.examples.hdfs.client;

import com.cczu.examples.hdfs.rpc.model.MkdirRequest;
import com.cczu.examples.hdfs.rpc.model.MkdirResponse;
import com.cczu.examples.hdfs.rpc.model.ShutdownRequest;
import com.cczu.examples.hdfs.rpc.model.ShutdownResponse;
import com.cczu.examples.hdfs.rpc.service.NameNodeServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

/**
 * 文件系统实现类
 *
 * @author yjz
 * @date 2022/1/8
 */
public class FileSystemImpl implements FileSystem {
    private static final String NAMENODE_HOSTNAME = "localhost";
    private static final Integer NAMENODE_PORT = 50070;
    private NameNodeServiceGrpc.NameNodeServiceBlockingStub namenode;

    public FileSystemImpl() {
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
    @Override
    public void mkdir(String path) throws Exception {
        MkdirRequest mkdirRequest = MkdirRequest.newBuilder().setPath(path).build();
        MkdirResponse mkdirResponse = namenode.mkdir(mkdirRequest);
        System.out.println("创建目录的响应：" + mkdirResponse.getStatus());
    }

    @Override
    public void shutdown() throws Exception {
        ShutdownRequest shutdownRequest = ShutdownRequest.newBuilder().setCode(1).build();
        ShutdownResponse shutdownResponse = namenode.shutdown(shutdownRequest);
        System.out.println("优雅关闭" + shutdownResponse);
    }
}
