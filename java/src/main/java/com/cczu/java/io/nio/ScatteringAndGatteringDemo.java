package com.cczu.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringAndGatteringDemo {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(6676);
        // 绑定端口到socket
        serverSocketChannel.socket().bind(address);
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        // 等待客户端连接
        SocketChannel accept = serverSocketChannel.accept();
        int msgLen = 8;
        // 循环读取
        while (true){
            int cnt = 0;
            while (cnt < msgLen) {
                long read = accept.read(byteBuffers);
                cnt += read;
            }
            Arrays.stream(byteBuffers).map(item-> item.position()+"---"+item.limit()).forEach(System.out::println);
            // 将所有bytebuffer进行flip
            Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);
            // 写回客户端
            int write = 0;
            while (write <msgLen){
                long write1 = accept.write(byteBuffers);
                write += write1;
            }
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);
            System.out.println("cnt"+cnt);
        }
    }
}
