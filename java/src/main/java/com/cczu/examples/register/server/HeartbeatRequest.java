package com.cczu.examples.register.server;

/**
 * @author yjz
 * @date 2021/10/13
 */
public class HeartbeatRequest {
    /**
     * 服务实例id
     */
    private String serviceInstanceId;
    /**
     * 服务名称
     */
    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }
}
