package com.cczu.examples.register.client;

/**
 * 代表一个服务实力
 * 包含一个服务的所有信息
 * ip,port,服务实例id，契约信息
 *
 * @author yjz
 * @date 2021/10/13
 */
public class ServiceInstance {
    /**
     * 判断服务实例不再存活的周期
     */
    private static final Long NOT_ALIVE_PERIOD = 90 * 1000L;
    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * ip
     */
    private String ip;
    /**
     * port
     */
    private int port;
    /**
     * 主机名
     */
    private String hostname;
    /**
     * 服务实例
     */
    private String serviceInstanceId;


    public ServiceInstance() {

    }


    @Override
    public String toString() {
        return "ServiceInstance{" +
                "serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", hostname='" + hostname + '\'' +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                '}';
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }
}
