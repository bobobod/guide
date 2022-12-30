package com.cczu.java.io.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {
    public static void main(String[] args) throws Exception{
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO)) // 增加一个日志处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            // 使用http的编解码器
                            pipeline.addLast(new HttpServerCodec());
                            // 是以块方式写，
                            pipeline.addLast(new ChunkedWriteHandler());
                            // http数据在传输中是分段的，HttpObjectAggregator 可以将多个段聚合起来
                            // 这也是为什么，当浏览器发送大量数据时，会发出多次http请求
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            // 对于websocket，他的数据是以frame传递的
                            // WebSocketFrame有6个子类
                            // 浏览器请求时 ws://localhost:7000/xxx
                            // WebSocketServerProtocolHandler 将http协议升级成ws协议，保持长连接
                            // 是通过一个状态码 101
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));
                            // 自定义handler
                            pipeline.addLast(new MyTextWebFrameHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(7000).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
