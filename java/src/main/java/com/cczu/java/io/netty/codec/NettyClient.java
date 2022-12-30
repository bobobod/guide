package com.cczu.java.io.netty.codec;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        // 客户端需要一个事件循环组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            // 创建客户端启动对象
            // 注意客户端使用的是Bootstrap 服务端使用的是ServerBootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(eventExecutors) // 设置线程组
                    .channel(NioSocketChannel.class)  // 设置客户端通信的实现类
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyClientHandler()); // 加入自己的处理器
                        }
                    });
            System.out.println("客户端 ok...");
            // 启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect(new InetSocketAddress("127.0.0.1", 6668)).sync();
            //  给关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }
}
