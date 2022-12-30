package com.cczu.java.io.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count;


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.channel().close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int len = msg.getLen();
        byte[] content = msg.getContent();
        System.out.println("服务端结束到消息如下 len=" + len + " content=" + new String(content, CharsetUtil.UTF_8));
        System.out.println(++count);
        // 回复消息

        String responseContent = UUID.randomUUID().toString();
        int respLen = responseContent.getBytes(CharsetUtil.UTF_8).length;
        MessageProtocol messageProtocol = new MessageProtocol();
        messageProtocol.setContent(responseContent.getBytes(CharsetUtil.UTF_8));
        messageProtocol.setLen(respLen);
        ctx.channel().writeAndFlush(messageProtocol);

    }
}
