package com.cczu.java.io.netty.dubborpc.provider;

import com.cczu.java.io.netty.dubborpc.iface.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String msg) {
        System.out.println("收到客户端消息"+msg);
        // 根据msg返回不同的结果
        if (msg != null){
            return "你好客户端，我已经收到了你的消息" + "【" + msg +"】，但是数据为空";
        }
        return "很高心认识你";
    }
}
