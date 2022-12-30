package com.cczu.java.io.netty.protocoltcp;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送10跳数据
        for (int i = 0; i < 10; i++) {
            String msg = "今天不上班" + i;
            byte[] content = msg.getBytes(CharsetUtil.UTF_8);
            int len = msg.getBytes(CharsetUtil.UTF_8).length;
            MessageProtocol messageProtocol = new MessageProtocol();
            messageProtocol.setContent(content);
            messageProtocol.setLen(len);
            ctx.writeAndFlush(messageProtocol);
//            ctx.channel().writeAndFlush(messageProtocol);
            /*
             ctx.writeAndFlush  和  ctx.channel().writeAndFlush 两者的区别
             1、ctx.writeAndFlush只会从当前的handler位置开始，往前找outbound执行
             2、ctx.pipeline().writeAndFlush与ctx.channel().writeAndFlush会从tail的位置开始，往前找outbound执行
             */

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("客户端接收到消息 len=" + len + " content=" + new String(content, CharsetUtil.UTF_8));
        System.out.println(++count);
    }
}
