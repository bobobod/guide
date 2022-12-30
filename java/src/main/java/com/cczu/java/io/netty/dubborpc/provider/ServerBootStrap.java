package com.cczu.java.io.netty.dubborpc.provider;


import com.cczu.java.io.netty.dubborpc.netty.NettyServer;

/**
 * ServerBootStrap 会启动一个服务提供者 NettyServer
 */
public class ServerBootStrap {
    public static void main(String[] args) {
        NettyServer.startServer("127.0.0.1",7001);
    }
}
