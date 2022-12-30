package com.cczu.java.io.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NIOIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel open = SocketChannel.open(new InetSocketAddress(7777));
        String fileName = "1.txt";
        FileChannel channel = new FileInputStream(fileName).getChannel();
        // linux下一个transferTo 方法就可以传输
        // window中transferTo每次最大只能传输8M
        // transferTo 底层使用零拷贝
        long l = channel.transferTo(0, channel.size(), open);
        System.out.println("发送的总的字节数"+l);
        channel.close();
        open.close();
    }
}
