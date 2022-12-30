package com.cczu.java.concurrent.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简易线程池实现
 */
public class ThreadPoolTrade implements Executor {

    private final AtomicInteger ctl = new AtomicInteger(0);
    private volatile int corePoolSize;
    private volatile int maxPoolSize;
    private final BlockingQueue<Runnable> workQueue;

    public ThreadPoolTrade(int corePoolSize, int maxPoolSize, BlockingQueue<Runnable> workQueue) {
        this.corePoolSize = corePoolSize;
        this.maxPoolSize = maxPoolSize;
        this.workQueue = workQueue;
    }


    @Override
    public void execute(Runnable command) {
        int cnt = ctl.get();
        if (cnt <= corePoolSize) {
            if (!addWorker(command)) {
                reject();
            }
            return;
        }
        if (!workQueue.offer(command)) {
            if (!addWorker(command)) {
                reject();
            }
        }
    }

    private boolean addWorker(Runnable command) {
        if (ctl.get() >= maxPoolSize) return false;
        Worker worker = new Worker(command);
        worker.thread.start();
        ctl.getAndIncrement();
        return true;
    }

    private final class Worker implements Runnable {
        final Thread thread;
        Runnable firstTask;

        public Worker(Runnable firstTask) {
            this.thread = new Thread(this);
            this.firstTask = firstTask;
        }

        @Override
        public void run() {
            Runnable task = firstTask;
            try {
                while (task != null || (task = getTask()) != null) {
                    task.run();
                    if (ctl.get() > maxPoolSize) {
                        break;
                    }
                    task = null;
                }
            } finally {
                ctl.decrementAndGet();
            }
        }

        private Runnable getTask() {
            for (; ; ) {
                try {
                    System.out.println("workQueue size :" + workQueue.size());
                    return workQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void reject() {
        throw new RuntimeException("ERROR!");
    }

    public static void main(String[] args) {
        /*
         *左移一位相当于乘以2的1次方，左移n位就相当于乘以2的n次方。
         * 右移一位相当于除以2的1次方，右移n位就相当于除以2的n次方。
         * 计算机中存放的都是补码:
         * 正数：原码-反码-补码 都是一致的
         * 负数：原码-反码（除符号位其他取反）-补码（反码+1）
         *
         * <<      :     左移运算符，num << 1,相当于num乘以2
         * >>      :     右移运算符，num >> 1,相当于num除以2
         * >>>     :     无符号右移，忽略符号位，空位都以0补齐
         */
        System.out.println(-1 >> 2);
        System.out.println(-1 >>> 1);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.SIZE);
        System.out.println(Double.parseDouble(String.valueOf(-Math.pow(2, 31))));
        ThreadPoolTrade threadPoolTrade = new ThreadPoolTrade(2, 5, new ArrayBlockingQueue<>(5));
        for (int i = 0; i < 10; i++) {

            int finalI = i;
            threadPoolTrade.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("任务编号" + finalI);
            });
        }

    }
}
