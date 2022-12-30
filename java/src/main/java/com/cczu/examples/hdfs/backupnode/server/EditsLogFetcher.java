package com.cczu.examples.hdfs.backupnode.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author yjz
 * @date 2022/1/10
 */
public class EditsLogFetcher extends Thread {
    private static final Integer BACKUP_NODE_FETCH_SIZE = 10;

    private BackupNode backupNode;
    private NameNodeRpcClient nameNodeRpcClient;
    private FSNameSystem nameSystem;

    public EditsLogFetcher(BackupNode backupNode, FSNameSystem nameSystem) {
        this.backupNode = backupNode;
        this.nameSystem = nameSystem;
        this.nameNodeRpcClient = new NameNodeRpcClient();
    }

    @Override
    public void run() {
        System.out.println("backNode 抓取的线程开启了");
        while (backupNode.isRunning()) {
            try {

                JSONArray editsLogs = nameNodeRpcClient.fetchEditsLog();
                if (editsLogs.size() == 0) {
                    System.out.println("没有拉取到任何editslog");
                    Thread.sleep(1000);
                    continue;
                }
                if (editsLogs.size() < BACKUP_NODE_FETCH_SIZE) {
                    Thread.sleep(1000);
                    System.out.println("拉取到的editslog不足10条数据，等待1s后继续拉取");
                }
                for (int i = 0; i < editsLogs.size(); i++) {
                    JSONObject editsLog = editsLogs.getJSONObject(i);
                    System.out.println("拉取到一条editslog：" + JSON.toJSONString(editsLog));
                    String op = editsLog.getString("OP");
                    if ("MKDIR".equals(op)) {
                        String path = editsLog.getString("PATH");
                        nameSystem.mkdir(editsLog.getLongValue("txid"), path);
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
