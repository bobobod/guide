package com.cczu.java.io.netty.inboundandoutboundhandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyByteToLongDecoder extends ByteToMessageDecoder {
    /**
     * decode方法会被调用多次，直到确定没有新的数据被添加到list
     * @param ctx 上下文
     * @param in 入站的byteBuf
     * @param out list集合，将解码后的数据传给下一个handler
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 需要判断有8个字节才可以读取一个long
        System.out.println("MyByteToLongDecoder decode invoke...");
        if (in.readableBytes() > 8){
            out.add(in.readLong());
        }
    }
}
