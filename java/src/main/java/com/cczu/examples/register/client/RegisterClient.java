package com.cczu.examples.register.client;

import java.util.UUID;

/**
 * 在服务中引用，负责和register-server通信
 *
 * @author yjz
 * @date 2021/10/12
 */
public class RegisterClient {

    private static final String SERVICE_NAME = "inventory-service";
    private static final String IP = "127.0.0.1";
    private static final String HOSTNAME = "host01";
    private static final int PORT = 9000;
    private HttpSender httpSender;
    private String serviceInstanceId;
    /**
     * 客户端缓存的注册表
     */
    private ClientCachedServiceRegistry registry;
    /**
     * 心跳线程
     */
    private HeartbeatWorker heartbeatWorker;
    /**
     * 服务实例是否正在运行
     */
    private volatile Boolean isRunning;

    public RegisterClient() {
        this.serviceInstanceId = UUID.randomUUID().toString().replace("-", "");
        this.httpSender = new HttpSender();
        this.heartbeatWorker = new HeartbeatWorker();
        this.isRunning = true;
    }


    public static void main(String[] args) throws InterruptedException {
        RegisterClient registerClient = new RegisterClient();
        registerClient.start();
        //注意： 执行程序会开启一个jvm进程，main线程在执行启动client线程就结束了，但是工作线程还在，所以jvm进程不会退出

        Thread.sleep(5000);
        registerClient.shutdown();

    }

    /**
     * 启动registerclient
     */
    public void start() {
        try {
            // 一旦启动了这个组建之后，他负责开启一个线程向register-server发送请求，注册这个服务
            // 注册成功之后，就会开启另外一个线程来维护心跳
            RegisterWorker registerWorker = new RegisterWorker(serviceInstanceId);
            registerWorker.start();
            // 等待注册线程全部执行完成 在mian线程中调用其他线程的join方法，main线程会阻塞住，他会等待其他线程的代码逻辑执行完毕
            registerWorker.join();
            // yield 方法，会通知cpu调度器停止执行当前线程
//            registerWorker.yield();
            this.heartbeatWorker.start();
            // 初始化缓存
            this.registry = new ClientCachedServiceRegistry(this, httpSender);
            this.registry.initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void shutdown() {
        this.isRunning = false;
        // 结合运行标志性位 通过interrupt打断一个线程
        this.heartbeatWorker.interrupt();
        this.registry.destroy();
        // 取消注册
        this.httpSender.cancel(SERVICE_NAME, serviceInstanceId);
    }


    private class RegisterWorker extends Thread {
        private String serviceInstanceId;

        public RegisterWorker(String serviceInstanceId) {
            this.serviceInstanceId = serviceInstanceId;
        }

        private RegisterWorker() {
        }

        @Override
        public void run() {
            RegisterRequest request = new RegisterRequest();
            request.setServiceName(SERVICE_NAME);
            request.setIp(IP);
            request.setHostname(HOSTNAME);
            request.setPort(PORT);
            request.setServiceInstanceId(serviceInstanceId);
            RegisterResponse response = httpSender.register(request);
            System.out.println("服务注册结果：" + response.toString());
            if (RegisterResponse.SUCCESS.equals(response.getStatus())) {
            } else {
                return;
            }
        }
    }


    /**
     * 一般这个线程会作为client的内部类
     */
    private class HeartbeatWorker extends Thread {
        @Override
        public void run() {

            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setServiceInstanceId(serviceInstanceId);
            heartbeatRequest.setServiceName(SERVICE_NAME);
            HeartbeatResponse heartbeatResponse;
            while (isRunning) {
                try {
                    // 发送心跳
                    heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                    System.out.println("心跳结果为：" + heartbeatResponse.toString());
                    Thread.sleep(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 服务是否正常运行
     *
     * @return
     */
    public Boolean isRunning() {
        return this.isRunning;
    }
}

