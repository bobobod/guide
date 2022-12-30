package com.cczu.examples.register.client;


import java.util.HashMap;
import java.util.Map;

/**
 * 服务注册中心的客户端缓存的一个服务注册表
 *
 * @author yjz
 * @date 2021/12/28
 */
public class ClientCachedServiceRegistry {
    /**
     * 负责拉取注册表到客户端进行缓存的后台线程
     */
    private Daemon daemon;
    /**
     * 客户端缓存的服务注册表
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();
    /**
     * 客户端
     */
    private RegisterClient registerClient;
    /**
     * 服务拉取注册表时间间隔
     */
    private static final Long SERVICE_REGISTRY_FETCH_INTERVAL = 30 * 1000L;
    /**
     * http通信组件
     */
    private HttpSender httpSender;

    public ClientCachedServiceRegistry(RegisterClient registerClient, HttpSender httpSender) {
        this.daemon = new Daemon();
        this.registerClient = registerClient;
        this.httpSender = httpSender;
    }

    /**
     * 初始化
     */
    public void initialize() {
        this.daemon.start();
    }

    /**
     * 销毁
     */
    public void destroy(){
        this.daemon.interrupt();
    }

    /**
     * 负责定时拉取注册表到本地来进行缓存
     */
    private class Daemon extends Thread {
        @Override
        public void run() {
            while (registerClient.isRunning()) {
                try {
                    registry = httpSender.fetchServiceRegistry();
                    Thread.sleep(SERVICE_REGISTRY_FETCH_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取注册表
     *
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> getRegistry() {
        return registry;
    }

}
