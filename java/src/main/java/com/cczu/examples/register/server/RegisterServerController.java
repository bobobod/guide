package com.cczu.examples.register.server;

import java.util.Map;

/**
 * @author yjz
 * @date 2021/10/13
 */
public class RegisterServerController {
    private ServiceRegistry serviceRegistry = ServiceRegistry.getInstance();

    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response = new RegisterResponse();
        try {
            ServiceInstance instance = new ServiceInstance();
            instance.setHostname(request.getHostname());
            instance.setIp(request.getIp());
            instance.setPort(request.getPort());
            instance.setServiceName(request.getServiceName());
            instance.setServiceInstanceId(request.getServiceInstanceId());
            serviceRegistry.register(instance);

            // 更新自我保护机制阈值
            synchronized (SelfProtectionPolicy.class) {
                SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
                selfProtectionPolicy.setExpectedHeartbeatRate(selfProtectionPolicy.getExpectedHeartbeatRate() + 2);
                selfProtectionPolicy.setExpectedHeartbeatThreshold((long) (selfProtectionPolicy.getExpectedHeartbeatRate() * 0.85));
            }

            response.setStatus(RegisterResponse.SUCCESS);
        } catch (Exception e) {
            response.setStatus(RegisterResponse.FAILURE);
        }
        return response;
    }

    public HeartbeatResponse heartbeat(HeartbeatRequest request) {
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        try {
            // 对服务实例进行续约
            ServiceInstance serviceInstance = serviceRegistry.getServiceInstance(request.getServiceName(), request.getServiceInstanceId());
            serviceInstance.renew();
            // 记录每分钟心跳次数
            HeartbeatMessuredRate heartbeatMessuredRate = HeartbeatMessuredRate.getInstance();
            heartbeatMessuredRate.increment();

            heartbeatResponse.setStatus(HeartbeatResponse.SUCCESS);

        } catch (Exception e) {
            heartbeatResponse.setStatus(HeartbeatResponse.FAILURE);
        }
        return heartbeatResponse;
    }

    /**
     * 拉取服务注册表
     *
     * @return
     */
    public Map<String, Map<String, ServiceInstance>> fetchServiceRegistry() {
        return serviceRegistry.getRegistry();
    }

    /**
     * 服务下线
     *
     * @param serviceName
     * @param serviceInstanceId
     */
    public void cancel(String serviceName, String serviceInstanceId) {
        System.out.println("服务实例【" + serviceName + "," + serviceInstanceId + "】下线了");
        serviceRegistry.remove(serviceName, serviceInstanceId);
        // 更新自我保护机制阈值
        synchronized (SelfProtectionPolicy.class) {
            SelfProtectionPolicy selfProtectionPolicy = SelfProtectionPolicy.getInstance();
            selfProtectionPolicy.setExpectedHeartbeatRate(selfProtectionPolicy.getExpectedHeartbeatRate() - 2);
            selfProtectionPolicy.setExpectedHeartbeatThreshold((long) (selfProtectionPolicy.getExpectedHeartbeatRate() * 0.85));
        }

    }
}
