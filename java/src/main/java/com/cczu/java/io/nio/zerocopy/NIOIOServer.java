package com.cczu.java.io.nio.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(7777));
        ByteBuffer buf = ByteBuffer.allocate(4096);
        while (true) {
            SocketChannel accept = serverSocket.accept();
            int read = 0;
            while ((read = accept.read(buf)) != -1) {

            }
            // 倒带
            buf.rewind();
        }
    }
}
