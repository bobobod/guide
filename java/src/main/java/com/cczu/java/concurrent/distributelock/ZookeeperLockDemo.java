package com.cczu.java.concurrent.distributelock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author yjz
 * @date 2022/3/22
 */
public class ZookeeperLockDemo {
    private static CuratorFramework getZkClient() {
        String zkServerAddress = "127.0.0.1:2181";
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3, 5000);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                .connectString(zkServerAddress)
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();
        return zkClient;
    }

    public static void main(String[] args) {
        CuratorFramework zkClient = getZkClient();
        String lockPath = "/lock";
        InterProcessLock lock = new InterProcessMutex(zkClient, lockPath);
        for (int i = 0; i < 10; i++) {
            new Thread(new TestThread(i, lock)).start();
        }

    }

    static class TestThread extends Thread {
        private int threadFlag;
        private InterProcessLock lock;

        public TestThread(int threadFlag, InterProcessLock lock) {
            this.threadFlag = threadFlag;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                lock.acquire(1000, TimeUnit.MILLISECONDS);
                System.out.println(Thread.currentThread().getName() + " " + threadFlag + "线程获取到锁");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
