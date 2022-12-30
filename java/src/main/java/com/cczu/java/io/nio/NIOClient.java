package com.cczu.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务端的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 6666);
        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("连接需要时间，客户端不会阻塞");
            }
        }
        String str = "hello world";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 将buffer数据写入channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
