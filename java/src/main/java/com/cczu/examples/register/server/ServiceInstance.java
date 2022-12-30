package com.cczu.examples.register.server;

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
    /**
     * 契约
     */
    private Lease lease;

    public ServiceInstance() {
        this.lease = new Lease();
    }

    private class Lease {


        /**
         * 心跳时间，需要是volatile
         */
        private volatile Long latestHeartbeatTime = System.currentTimeMillis();

        /**
         * 续约，每发送一次心跳，client和service之间维护一个契约
         */
        public void renew() {
            this.latestHeartbeatTime = System.currentTimeMillis();
            System.out.println("服务实例【" + serviceInstanceId + "】，完成续约");

        }

        /**
         * 判断当前服务实例的契约是否存活
         *
         * @return
         */
        public Boolean isAlive() {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.latestHeartbeatTime > NOT_ALIVE_PERIOD) {
                System.out.println("服务实例【" + serviceInstanceId + "】服务实例不再存活");
                return false;
            }
            System.out.println("服务实例【" + serviceInstanceId + "】服务实例存活");
            return true;
        }
    }

    /**
     * 服务续约
     */
    public void renew() {
        this.lease.renew();
    }

    /**
     * 服务是否存活
     *
     * @return
     */
    public Boolean isAlive() {
        return this.lease.isAlive();
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "serviceName='" + serviceName + '\'' +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", hostname='" + hostname + '\'' +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                ", lease=" + lease +
                '}';
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
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
