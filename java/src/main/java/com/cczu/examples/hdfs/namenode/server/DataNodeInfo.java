package com.cczu.examples.hdfs.namenode.server;

/**
 * 描述datanode的信息
 *
 * @author yjz
 * @date 2022/1/4
 */
public class DataNodeInfo {
    private String ip;
    private String hostname;
    private long latestHeartbeatTime;

    public DataNodeInfo(String ip, String hostname) {
        this.ip = ip;
        this.hostname = hostname;
        this.latestHeartbeatTime = System.currentTimeMillis();
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

    public long getLatestHeartbeatTime() {
        return latestHeartbeatTime;
    }

    public void setLatestHeartbeatTime(long latestHeartbeatTime) {
        this.latestHeartbeatTime = latestHeartbeatTime;
    }
}
