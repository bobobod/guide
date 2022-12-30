package com.cczu.java.io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyByteBuf {
    public static void main(String[] args) {
        // 创建一个ByteBuf
        // 创建一个对象，该对象包含一个数组arr， byte[10]
        // 在netty中 buf不需要flip 进行反转
        // 底层维护了 readerIndex和writerIndex
        // 分成3个区域
        // 0～readerIndex  已经读取的区域
        // readerIndex～writerIndex  可读取的区域
        // writerIndex～capacity 可写的区域
        ByteBuf buf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buf.writeByte(i);
        }
        for (int i = 0; i < buf.capacity(); i++) {
            System.out.println(buf.readByte());
        }
    }
}
