package com.cczu.examples.register.server;

/**
 * 注册请求
 *
 * @author yjz
 * @date 2021/10/12
 */
public class RegisterRequest {
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * ip
     */
    private String ip;
    /**
     * 主机名
     */
    private String hostname;
    /**
     * 端口
     */
    private int port;
    /**
     * 服务实例id
     */
    private String serviceInstanceId;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                '}';
    }
}
