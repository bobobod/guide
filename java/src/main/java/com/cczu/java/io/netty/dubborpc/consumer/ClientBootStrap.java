package com.cczu.java.io.netty.dubborpc.consumer;

import com.cczu.java.io.netty.dubborpc.iface.HelloService;
import com.cczu.java.io.netty.dubborpc.netty.NettyClient;

public class ClientBootStrap {
    public static final String provideName = "HelloServer#hello#";

    public static void main(String[] args) {
        NettyClient consumer = new NettyClient();
        HelloService service = (HelloService) consumer.getBean(HelloService.class, provideName);
        System.out.println(service.hello("hlerjqwlrqjwerl"));

    }
}
