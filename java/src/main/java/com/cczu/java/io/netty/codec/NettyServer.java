package com.cczu.java.io.netty.codec;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    static final EventLoopGroup group = new DefaultEventLoopGroup(2);

    public static void main(String[] args) throws InterruptedException {
        // 创建 BossGroup 和 workerGroup 两个线程组
        // 1.bossGroup只是处理连接请求 真正和客户端进行业务处理时，会交给workerGroup
        // 2. 两个都是无限循环
        // bossGroup 和 workerGroup 含有子线程（NioEventLoop)的个数
        // 默认为 cpu核数 * 2
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务器端的启动对象，配置参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 使用链式编程来设置
            bootstrap.group(bossGroup, workerGroup) // 这是设置两个线程组
                    .channel(NioServerSocketChannel.class) // 使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG, 128) // 设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, true) // 设置保持活动连接状态
                    .handler(null) //该handler对应bossGroup的handler，而childHandler对应的是workerGroup的handler
                    .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个管道测试对象（匿名对象）
                        // 给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户的socketChannel" + socketChannel.hashCode());

                            // 如果我们addLast添加handler时，前面有指定线程池，会优先压入到该线程池中
                            socketChannel.pipeline().addLast(group,new NettyServerHandler());
//                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    }); // 给我们的workerGroup的EventLoop对应的管道处理器
            System.out.println("服务器 is ready ...");
            // 绑定一个端口并且同步，生成一个channelFuture对象
            // 启动服务器
            ChannelFuture channelFuture = bootstrap.bind(6668)
                    .sync(); // 等待异步操作执行完毕
            // 给注册监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("监听端口成功");
                    } else {
                        System.out.println("监听端口失败");
                    }
                }
            });
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture()
                    .sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
