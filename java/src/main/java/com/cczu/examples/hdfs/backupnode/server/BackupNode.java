package com.cczu.examples.hdfs.backupnode.server;

/**
 * @author yjz
 * @date 2022/1/10
 */
public class BackupNode {
    private volatile Boolean isRunning = true;

    private FSNameSystem nameSystem;

    public static void main(String[] args) throws InterruptedException {
        BackupNode backupNode = new BackupNode();
        backupNode.init();
        backupNode.start();
    }

    public void init() {
        nameSystem = new FSNameSystem();
    }

    private void start() throws InterruptedException {
        EditsLogFetcher editsLogFetcher = new EditsLogFetcher(this, nameSystem);
        editsLogFetcher.setDaemon(true);
        editsLogFetcher.start();
        FsCheckPointer fsCheckPointer = new FsCheckPointer(this, nameSystem);
        fsCheckPointer.start();
        while (isRunning) {
            Thread.sleep(1000);
        }
    }

    public Boolean isRunning() {
        return isRunning;
    }
}
