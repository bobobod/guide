package com.cczu.examples.register.server;

import java.util.UUID;

/**
 * daemon线程和工作线程的区别
 * daemon线程会跟着jvm进程一起退出，不会像工作线程那样组织jvm进程退出
 * 直接启动线程是非daemon线程、
 * <p>
 * java中是有父线程的概念的，线程在哪里创建的这个县城，那么他的父线程就是谁
 * java中，线程都是有名称的，main线程的名字叫做main，其他的线程一般叫做thread-0之类的
 * 线程是不可以重复调用执行的 {@link Thread#start()}
 *
 * <p>
 * <p>
 * 静态内部类和非静态内部类的区别有：
 * 静态内部类是指被声明为static的内部类，可不依赖外部类实例化；而非静态内部类需要通过生成外部类来间接生成。
 * 静态内部类只能访问外部类的静态成员变量和静态方法，而非静态内部类由于持有对外部类的引用，可以访问外部类的所用成员
 * <p>
 * <p>
 * interrupt 打断线程的话，其实就是修改那个线程的一个interrupt的标志位，打断它以后，interrupt标志位就是true了，
 * 1. 在线程内部可以通过判断这个标志位去执行代码逻辑
 * 2. 还有一个非常常用的用法，就是打断一个线程的休眠 sleep
 */
public class RegisterServer {
    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " 线程组：" + Thread.currentThread().getThreadGroup());
        RegisterServerController controller = new RegisterServerController();
        RegisterRequest registerRequest = new RegisterRequest();
        String serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        registerRequest.setHostname("inventory-service-id");
        registerRequest.setIp("127.0.0.1");
        registerRequest.setPort(3306);
        registerRequest.setServiceInstanceId(serviceInstanceId);
        registerRequest.setServiceName("inventory-service");
        controller.register(registerRequest);

        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);
        heartbeatRequest.setServiceName("inventory-service");
        controller.heartbeat(heartbeatRequest);

        // 开启一个线程，检查微服务的存活状态
        ServiceAliveMonitor serviceAliveMonitor = new ServiceAliveMonitor();
        serviceAliveMonitor.start();


        while (true) {
            Thread.sleep(3 * 1000);
        }
    }
}
