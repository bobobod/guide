package com.cczu.java.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            // 得到选择器
            selector = Selector.open();
            // 获取serverSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            // 绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            // 设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 注册到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            listen();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void listen() {
        while (true) {
            try {
                int cnt = selector.select(1000);
                if (cnt > 0) {
                    // 有事件处理  遍历selectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        if (next.isAcceptable()) {
                            SocketChannel accept = serverSocketChannel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                            System.out.println(accept.getRemoteAddress() + "上线了...");
                        }
                        if (next.isReadable()) {
                            // 处理读
                            read(next);
                        }
                        // 将当前key删除
                        iterator.remove();
                    }
                } else {
                    System.out.println("等待中...");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void read(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            // 创建ByteBuffer
            ByteBuffer buffer = ByteBuffer.allocate(10);
            int read = channel.read(buffer);
            if (read > 0) {
                String msg = new String(buffer.array());
                System.out.println("从客户端" + channel.getRemoteAddress() + "接受到 " + msg);
                // 向其他客户端转发消息(排除自己）
                sendInfoToOtherClient(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "下线了");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中...");
        // 获取所有注册了的key
        for (SelectionKey key : selector.keys()) {
            Channel target = key.channel();
            // 排除自己
            if (target instanceof SocketChannel && !target.equals(self)) {
                SocketChannel socket = (SocketChannel) target;
                // 将msg转储到buffer
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                // 将buffer的数据写入buf
                socket.write(wrap);
            }

        }

    }

    public static void main(String[] args) {
        GroupChatServer server = new GroupChatServer();
    }
}
