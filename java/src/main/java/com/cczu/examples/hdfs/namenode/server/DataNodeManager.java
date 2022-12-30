package com.cczu.examples.hdfs.namenode.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理集群中所有的datanode
 *
 * @author yjz
 * @date 2022/1/4
 */
public class DataNodeManager {
    /**
     * 内存中维护的datanode集合
     */
    private Map<String, DataNodeInfo> dataNodes = new ConcurrentHashMap<>();

    public DataNodeManager() {
        DataNodeAliveMonitor dataNodeAliveMonitor = new DataNodeAliveMonitor();
        dataNodeAliveMonitor.setDaemon(true);
        dataNodeAliveMonitor.start();
    }

    /**
     * datanode进行注册
     *
     * @param ip
     * @param hostname
     */
    public Boolean register(String ip, String hostname) {
        DataNodeInfo dataNode = new DataNodeInfo(ip, hostname);
        dataNodes.put(ip + "-" + hostname, dataNode);
        return true;
    }

    public Boolean heartbeat(String ip,String hostname){
        System.out.println("接收到客户的请求");
        DataNodeInfo dataNodeInfo = dataNodes.get(ip + "-" + hostname);
        if (dataNodeInfo != null){
            dataNodeInfo.setLatestHeartbeatTime(System.currentTimeMillis());
        }
        return true;
    }

    class DataNodeAliveMonitor extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    List<String> toRemoveDataNodes = new ArrayList<>();
                    Iterator<DataNodeInfo> iter = dataNodes.values().iterator();
                    DataNodeInfo dataNodeInfo = null;
                    while (iter.hasNext()) {
                        dataNodeInfo = iter.next();
                        if (System.currentTimeMillis() - dataNodeInfo.getLatestHeartbeatTime() > 90 * 1000) {
                            toRemoveDataNodes.add(dataNodeInfo.getIp() + "-" + dataNodeInfo.getHostname());
                        }
                    }
                    if (!toRemoveDataNodes.isEmpty()) {
                        for (String toRemoveDataNode : toRemoveDataNodes) {
                            dataNodes.remove(toRemoveDataNode);
                        }
                    }
                    Thread.sleep(30 * 1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
