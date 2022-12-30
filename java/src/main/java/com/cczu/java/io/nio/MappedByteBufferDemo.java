package com.cczu.java.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByteBufferDemo {
    public static void main(String[] args) throws IOException {
        // 可以让文件直接在堆外内存中修改，操作系统不需要拷贝一次
        RandomAccessFile file = new RandomAccessFile("1.txt", "rw");
        FileChannel channel = file.getChannel();
        /*
        参数一：使用读写模式
        参数二：可以直接修改的起始位置
        参数三：映射内存的大小，即将1.txt的多少个字节映射到内存，可以直接修改的范围
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        map.put(4, (byte) 10);
        file.close();
    }
}
