package com.cczu.examples.register.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表 单例
 *
 * @author yjz
 * @date 2021/10/13
 */
public class ServiceRegistry {
    /**
     * 核心数据结构
     * <p>
     * Map<String, Map<String, ServiceInstance>>
     * 服务名称      服务实例id    服务
     */
    private Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();

    private static ServiceRegistry instance = new ServiceRegistry();

    private ServiceRegistry() {
    }

    public static ServiceRegistry getInstance() {
        return instance;
    }

    /**
     * 服务注册
     *
     * @param serviceInstance
     */
    public synchronized void register(ServiceInstance serviceInstance) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.computeIfAbsent(serviceInstance.getServiceName(), k -> new HashMap<>());
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(), serviceInstance);
        System.out.println("服务实例【" + serviceInstance + "】，完成注册");
        System.out.println("注册表：" + registry);
    }

    /**
     * 获取服务实例
     *
     * @param serviceName
     * @param serviceInstanceId
     * @return
     */
    public synchronized ServiceInstance getServiceInstance(String serviceName, String serviceInstanceId) {
        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        return serviceInstanceMap.get(serviceInstanceId);
    }

    /**
     * 获取整个注册表
     *
     * @return
     */
    public synchronized Map<String, Map<String, ServiceInstance>> getRegistry() {
        return this.registry;
    }

    /**
     * 从注册表中删除某个实例id
     *
     * @param serviceName
     * @param serviceInstanceId
     */
    public synchronized void remove(String serviceName, String serviceInstanceId) {
        System.out.println("服务实例【" + serviceInstanceId + "】从注册表中移除");

        Map<String, ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        serviceInstanceMap.remove(serviceInstanceId);
    }
}
