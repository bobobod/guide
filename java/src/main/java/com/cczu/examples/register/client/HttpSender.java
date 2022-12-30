package com.cczu.examples.register.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 负责各种http请求
 *
 * @author yjz
 * @date 2021/10/12
 */
public class HttpSender {

    public RegisterResponse register(RegisterRequest request) {
        System.out.println("发送请求进行注册");
        RegisterResponse resp = new RegisterResponse();
        resp.setStatus(RegisterResponse.SUCCESS);
        return resp;
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest request) {
        System.out.println("发送请求进行心跳");
        HeartbeatResponse response = new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }

    /**
     * 拉取服务注册表
     *
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> fetchServiceRegistry() {
        Map<String, Map<String, ServiceInstance>> registry = new HashMap<>();
        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setHostname("localhost");
        serviceInstance.setIp("127.0.0.1");
        serviceInstance.setPort(9000);
        serviceInstance.setServiceInstanceId("FINANCE-SERVICE-192.168.11.207:9000");
        serviceInstance.setServiceName("FINANCE-SERVICE");
        Map<String, ServiceInstance> serviceInstanceMap = new HashMap<>();
        serviceInstanceMap.put("FINANCE-SERVICE-192.168.11.207:9000", serviceInstance);
        registry.put("FINANCE-SERVICE", serviceInstanceMap);
        System.out.println("拉取注册表：" + registry);
        return registry;
    }

    /**
     * 服务下线
     *
     * @param serviceName       服务名称
     * @param serviceInstanceId 服务实例id
     */
    public void cancel(String serviceName, String serviceInstanceId) {
        System.out.println("服务实例【" + serviceName + "," + serviceInstanceId + "】下线了");
    }
}
