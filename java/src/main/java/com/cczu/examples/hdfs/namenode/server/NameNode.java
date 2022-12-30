package com.cczu.examples.hdfs.namenode.server;

/**
 * namenode 核心启动类
 *
 * @author yjz
 * @date 2021/12/30
 */
public class NameNode {

    /**
     * 负责管理元数据的核心组件 文件目录树
     */
    private FSNameSystem nameSystem;
    /**
     * 负责管理集群中所有datanode
     */
    private DataNodeManager datanodeManager;
    /**
     * namenode 对外提供rpc服务
     */
    private NameNodeRpcServer rpcServer;

    public NameNode() {
    }

    /**
     * 初始化
     */
    private void initialize() {
        this.nameSystem = new FSNameSystem();
        this.datanodeManager = new DataNodeManager();
        this.rpcServer = new NameNodeRpcServer(this.nameSystem, this.datanodeManager);
    }

    /**
     * 让namenode运行起来
     */
    private void start() throws Exception {
        this.rpcServer.start();
        this.rpcServer.blockUntilShutdown();
    }

    public static void main(String[] args) throws Exception {
        NameNode nameNode = new NameNode();
        nameNode.initialize();
        nameNode.start();
    }
}
