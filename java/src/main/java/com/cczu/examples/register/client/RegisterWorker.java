package com.cczu.examples.register.client;

/**
 * 负责和register-server发消息
 *
 * @author yjz
 * @date 2021/10/12
 */
public class RegisterWorker extends Thread {
    private static final String SERVICE_NAME = "inventory-service";
    private static final String IP = "127.0.0.1";
    private static final String HOSTNAME = "host01";
    private static final int PORT = 9000;
    private HttpSender httpSender;

    public RegisterWorker() {
        this.httpSender = new HttpSender();
    }

    @Override
    public void run() {
        RegisterRequest request = new RegisterRequest();
        request.setServiceName(SERVICE_NAME);
        request.setIp(IP);
        request.setHostname(HOSTNAME);
        request.setPort(PORT);
        RegisterResponse response = httpSender.register(request);
        System.out.println("服务注册结果：" + response.toString());
    }
}
