package com.cczu.java.io.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class NioHttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        // 向管道中加入处理器
        // 得到管道
        ChannelPipeline pipeline = ch.pipeline();
        // HttpServerCodec 是netty提供的编解码器
        pipeline.addLast("myHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("myNioHttpServerHandler",new NioHttpServerHandler());

    }
}
