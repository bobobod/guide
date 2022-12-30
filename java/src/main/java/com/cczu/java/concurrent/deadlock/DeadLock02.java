package com.cczu.java.concurrent.deadlock;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock02 {
    private static ReentrantLock lock01 = new ReentrantLock();
    private static ReentrantLock lock02 = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread = new Thread(new Lock01());
        Thread thread2 = new Thread(new Lock01());
        thread.start();
        thread2.start();
    }
    static class Lock01 implements Runnable{

        @Override
        public void run() {
            try {
                System.out.println("Lock1 running");
                while (true) {
                    if (lock01.tryLock(1, TimeUnit.MILLISECONDS)) {
                        System.out.println("Lock1 lock obj1");
                        if (lock02.tryLock(1, TimeUnit.MILLISECONDS)) {
                            System.out.println("Lock1 lock obj2");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock01.unlock();
                lock02.unlock();
            }
        }
        }

}
