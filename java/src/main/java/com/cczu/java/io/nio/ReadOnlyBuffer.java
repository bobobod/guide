package com.cczu.java.io.nio;

import java.nio.ByteBuffer;

public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocate(64);
        for (int i = 0; i < 64; i++) {
            buf.put((byte)i);
        }
        buf.flip();
        ByteBuffer byteBuffer = buf.asReadOnlyBuffer();
        // HeapByteBufferR
        System.out.println(byteBuffer.getClass());
        while (byteBuffer.hasRemaining()){
            System.out.println(byteBuffer.get());
        }
    }
}
