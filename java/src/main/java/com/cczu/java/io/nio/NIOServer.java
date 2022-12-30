package com.cczu.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 得到一个selector
        Selector selector = Selector.open();
        // 绑定一个端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非组塞
        serverSocketChannel.configureBlocking(false);
        // 把serverSocket注册到selector中，关注事件 OP_Accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 循环等待客户端连接
        while (true) {
            // 等待一秒，如果没有连接事件发生，返回
            if (selector.select(1000) == 0) {
                System.out.println("没有事件发生");
                continue;
            }
            // 获取到(已经获取到关注事件）相关的selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 反向获取通道
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                // 获取各个事件
                SelectionKey next = iterator.next();
                // 根据key对应的通道的事件做相应的响应
                if (next.isAcceptable()) {
                    // 有OP_ACCEPT 事件 新建 SocketChannel 本身方法是阻塞的，但是由于已知是有连接时，会立马建立
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将socketChannel设置为非阻塞的
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功");
                    // 将socketChannel注册到selector，同时关注OP_READ事件，同时给该channel关联一个buffer
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (next.isReadable()) {
                    //发生OP_READ,反向获取channel
                    SocketChannel channel = (SocketChannel) next.channel();
                    // 获取到关联的buffer
                    ByteBuffer buffer = (ByteBuffer) next.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端 " + new String(buffer.array()));
                }
                // 手动从集合中移除当前的key，防止重复创建
                iterator.remove();
            }
        }
    }
}
