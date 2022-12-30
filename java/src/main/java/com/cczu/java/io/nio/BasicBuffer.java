package com.cczu.java.io.nio;

import java.nio.IntBuffer;

/**
 * @author yjz
 */
public class BasicBuffer {
    public static void main(String[] args) {
        // buffer使用
        // 创建一个buffer，可以存放5个int
        IntBuffer buffer = IntBuffer.allocate(5);
        // 向buffer中存放数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i*2);
        }
        // 如何从buffer中读取数据
        // 将buffer进行读写切换
        buffer.flip();
        while (buffer.hasRemaining()){
            //Reads the int at this buffer's current position, and then increments the position.
            System.out.println(buffer.get());
        }
    }
}
