package com.cczu.examples.register.server;

import java.util.Map;

/**
 * 检测组件
 *
 * @author yjz
 * @date 2021/10/14
 */
public class ServiceAliveMonitor {
    /**
     * 检查实例存活的间隔
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60 * 1000L;

    private Daemon daemon;

    public ServiceAliveMonitor() {
        daemon = new Daemon();
        // 设置为daemon线程，daemon线程不会阻止jvm进程正常退出，不是核心工作线程
        // daemon线程会跟着jvm进程退出
        daemon.setDaemon(true);
        daemon.setName("serviceAliveMonitor");
        // 默认情况下daemon线程的父线程是main，线程组也是main线程组。线程组也是有父子关系的，如果指定了线程组的话，daemonThreadGroup 的线程组的父线程组是main线程组
        // 默认情况下所有线程的优先级都是5
//        ThreadGroup daemonThreadGroup = new ThreadGroup("daemon");

    }


    public void start() {
        daemon.start();
    }

    private class Daemon extends Thread {
        private ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();

        @Override
        public void run() {
            while (true) {
                try {
                    SelfProtectionPolicy instance = SelfProtectionPolicy.getInstance();
                    if (instance.isEnable()) {
                        Thread.sleep(CHECK_ALIVE_INTERVAL);
                        continue;
                    }
                    Map<String, Map<String, ServiceInstance>> registryMap = serviceRegistry.getRegistry();
                    for (String serviceName : registryMap.keySet()) {
                        Map<String, ServiceInstance> serviceInstanceMap = registryMap.get(serviceName);
                        for (ServiceInstance serviceInstance : serviceInstanceMap.values()) {
                            if (!serviceInstance.isAlive()) {
                                // 认为服务挂掉了
                                serviceRegistry.remove(serviceName, serviceInstance.getServiceInstanceId());
                                // 更新自我保护机制阈值
                                synchronized (SelfProtectionPolicy.class) {
                                    SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                                    selfProtectionPolicy.setExpectedHeartbeatRate(selfProtectionPolicy.getExpectedHeartbeatRate() - 2);
                                    selfProtectionPolicy.setExpectedHeartbeatThreshold((long) (selfProtectionPolicy.getExpectedHeartbeatRate() * 0.85));
                                }
                            }
                        }
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
