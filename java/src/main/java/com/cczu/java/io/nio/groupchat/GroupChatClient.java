package com.cczu.java.io.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            username = socketChannel.getLocalAddress().toString();
            System.out.println(username + "初始化完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendInfo(String info) {
        info = username + "说：" + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInfo() {
        try {
            int cnt = selector.select(1000);
            if (cnt > 0) {
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    if (key.isReadable()) {
                        // 得到相关的通道
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer allocate = ByteBuffer.allocate(10);
                        channel.read(allocate);
                        String msg = new String(allocate.array());
                        System.out.println(msg);
                    }
                    // 及时删除当前的selectionKey，防止重复操作
                    iter.remove();
                }

            } else {
//                System.out.println("没有可读的消息...");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        new Thread(()->{
            while (true){
                client.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送数据到服务端
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()){
            String next = scan.next();
            client.sendInfo(next);
        }
    }
}
