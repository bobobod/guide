package com.cczu.java.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 线程池机制
        ExecutorService pool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动...");
        while (true) {
            // 监听等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            pool.execute(() -> {
                handler(socket);
            });
        }
    }

    public static void handler(Socket socket) {
        try {
            System.out.println(Thread.currentThread().getId()+"---"+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            // 通过socket获取输入流
            InputStream ins = socket.getInputStream();
            // 循环读取
            while (true) {
                int cnt = ins.read(bytes);
                if (cnt != -1) {
                    System.out.println(new String(bytes, 0, cnt));
                } else {
                    break;
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } finally {
            System.out.println("关闭和client的连接");
            try {
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

    }
}
