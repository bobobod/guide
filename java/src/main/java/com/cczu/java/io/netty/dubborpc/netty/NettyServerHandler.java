package com.cczu.java.io.netty.dubborpc.netty;

import com.cczu.java.io.netty.dubborpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        // 获取客户端发送的消息，并调用服务
        System.out.println("收到消息" + msg);
        // 客户端在调用服务器的服务时，我们需要定义一个协议
        // 比如我们要求 每次发消息时都必须以某个字符串为头 HelloServer#hello#你好
        if (msg.startsWith("HelloServer#hello#")) {
            String result = new HelloServiceImpl().hello(msg.substring(msg.lastIndexOf('#') + 1));
            ctx.channel().writeAndFlush(result);
        }
    }
}
