package com.cczu.examples.hdfs.namenode.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

/**
 * @author yjz
 */
public class NameNodeRpcServer {

    private static final int DEFAULT_PORT = 50070;

    private Server server = null;
    /**
     * 负责管理元数据的核心组件
     */
    private FSNameSystem namesystem;
    /**
     * 负责管理集群中所有的datanode的组件
     */
    private DataNodeManager datanodeManager;

    public NameNodeRpcServer(
            FSNameSystem namesystem,
            DataNodeManager datanodeManager) {
        this.namesystem = namesystem;
        this.datanodeManager = datanodeManager;
    }

    public void start() throws IOException {
        // 启动一个rpc server，监听指定的端口号
        // 同时绑定好了自己开发的接口
        server = ServerBuilder
                .forPort(DEFAULT_PORT)
                .addService(new NameNodeServiceImpl(namesystem, datanodeManager))
                .build()
                .start();

        System.out.println("NameNodeRpcServer启动，监听端口号：" + DEFAULT_PORT);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                NameNodeRpcServer.this.stop();
            }
        });
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}