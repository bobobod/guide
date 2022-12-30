package com.cczu.java.io.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class MyMessageDecoder extends ReplayingDecoder<Void> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder decode invoke ...");
        int len = in.readInt();
        byte[] bytes = new byte[len];
        in.readBytes(bytes);
        // 封装成messageProtocol对象
        MessageProtocol msg = new MessageProtocol();
        msg.setLen(len);
        msg.setContent(bytes);
        out.add(msg);
    }
}
