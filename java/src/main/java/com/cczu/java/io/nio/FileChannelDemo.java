package com.cczu.java.io.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) throws IOException {
//        write();
//        read();
//        copy2File();
        copy2File2();
        return;
    }

    private static void read() throws IOException {
        File file = new File("hello.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        // 将channel的数据写入缓冲区
        int read = channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();

    }

    private static void write() throws IOException {
        String str = "hello world";
        // 创建一个输出流->channel
        FileOutputStream outputStream = new FileOutputStream(new File("hello.txt"));
        // 通过输出流获取channel  真实类型是FileChannelImpl
        FileChannel channel = outputStream.getChannel();
        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // str放入byteBuffer
        byteBuffer.put(str.getBytes());
        // 对byteBuffer切换为可读
        byteBuffer.flip();
        // 将byteBuffer的数据写入到fileChannel
        channel.write(byteBuffer);
        outputStream.close();
    }

    private static void copy2File() throws IOException {
        FileInputStream inputStream = new FileInputStream("1.txt");
        FileChannel inputChannel = inputStream.getChannel();
        FileOutputStream outputStream = new FileOutputStream("2.txt");
        FileChannel outputChannel = outputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            // 注意：先清空buffer
            buffer.clear();
            // 循环读取
            int read = inputChannel.read(buffer);
            if (read == -1) {
                break;
            }
            // 将buffer的数据读取 并写入到outputChannel
            buffer.flip();
            outputChannel.write(buffer);
        }
        inputStream.close();
        outputStream.close();
    }

    private static void copy2File2() throws IOException {
        FileInputStream srcInputStream = new FileInputStream("1.txt");
        FileChannel src = srcInputStream.getChannel();
        FileOutputStream distOutputStream = new FileOutputStream("2.txt");
        FileChannel dist = distOutputStream.getChannel();
        dist.transferFrom(src, 0, src.size());
        src.close();
        dist.close();
        srcInputStream.close();
        distOutputStream.close();
    }

}
