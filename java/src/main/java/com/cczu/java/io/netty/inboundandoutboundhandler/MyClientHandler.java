package com.cczu.java.io.netty.inboundandoutboundhandler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("从服务器"+ctx.channel().remoteAddress()+"收到 "+msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 传入的数据类型需要和处理的数据类型一致
        ctx.channel().writeAndFlush(12L);

        System.out.println("客户端发送数据...");
//        ctx.channel().writeAndFlush(Unpooled.compositeBuffer())
    }
}
