package com.cczu.java.io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.util.Arrays;

public class NettyByteBuf2 {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        if (buf.hasArray()) {
            byte[] array = buf.array();
            String s = Arrays.toString(array);
            System.out.println(s);
            System.out.println(buf.arrayOffset());
            System.out.println(buf.readerIndex());
            System.out.println(buf.writerIndex());
            System.out.println(buf.capacity());
            System.out.println(buf.readableBytes());
            System.out.println();

        }
    }
}
