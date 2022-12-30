package com.cczu.java.io.netty.dubborpc.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

public class NettyClientHandler extends SimpleChannelInboundHandler<String> implements Callable<String> {
    private ChannelHandlerContext context;
    private String result;
    private String para;

    // 被代理对象调用，发送数据给服务器 wait
    @Override
    public synchronized String call() throws Exception {
        context.channel().writeAndFlush(this.para);
        wait();
        return result;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered");
//        this.context = ctx;
    }

    /**
     * 与服务器创建成功时就会被调用
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        this.context = ctx;
    }
//    @Override
//    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        result = msg.toString();
//        notifyAll();
//    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        result = msg;
        notifyAll();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void setPara(String para) {
        this.para = para;
    }
}
