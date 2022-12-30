package com.cczu.examples.register.server;

/**
 * 心跳测量计数器
 *
 * @author yjz
 * @date 2021/12/28
 */
public class HeartbeatMessuredRate {
    private static HeartbeatMessuredRate instance = new HeartbeatMessuredRate();
    /**
     * 最近一分钟心跳的次数  atomic 改造
     */
    private long latestMinuteHeartbeatRate = 0L;
    /**
     * 最近一分钟的时间戳
     */
    private long latestMinuteTimeStamp = System.currentTimeMillis();

    private HeartbeatMessuredRate() {
        Daemon daemon = new Daemon();
        // 设置为后台进程
        daemon.setDaemon(true);
        daemon.start();
    }

    public static HeartbeatMessuredRate getInstance() {
        return instance;
    }

    /**
     * 增加一次最近一分钟的心跳次数
     */
    public synchronized void increment() {
        this.latestMinuteHeartbeatRate++;
    }

    /**
     * 获取最近一分钟的心跳次数
     *
     * @return
     */
    public synchronized long get() {
        return latestMinuteHeartbeatRate;
    }

    private class Daemon extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (HeartbeatMessuredRate.class) {
                        long currentTime = System.currentTimeMillis();
                        if (currentTime - latestMinuteHeartbeatRate > 60 * 1000) {
                            latestMinuteHeartbeatRate = 0L;
                            latestMinuteTimeStamp = System.currentTimeMillis();
                        }
                    }
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
