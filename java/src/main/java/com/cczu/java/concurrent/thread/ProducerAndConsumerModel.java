package com.cczu.java.concurrent.thread;

import java.util.LinkedList;

/**
 * 生产者-消费者 模型
 */
public class ProducerAndConsumerModel {
    static class Container<T> {
        private final LinkedList<T> list = new LinkedList<>();
        private final int CAPACITY = 100;

        public synchronized void put(T t) {
            while (list.size() == CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            list.add(t);
            System.out.println(Thread.currentThread().getName() + "生产了：" + t);
            notifyAll();
        }

        public synchronized void get() {
            while (list.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            T t = list.removeFirst();
            System.out.println(Thread.currentThread().getName() + "消费了：" + t);
            notifyAll();
        }
    }

    static class Producer implements Runnable {
        Container container;
        String name;

        public Producer(Container container, String name) {
            this.container = container;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                container.put(name);
            }
        }
    }

    static class Consumer implements Runnable {
        Container container;


        public Consumer(Container container) {
            this.container = container;
        }

        @Override
        public void run() {
            while (true) {
                container.get();
            }
        }
    }

    public static void main(String[] args) {
        Container<String> container = new Container<>();

        new Thread(new Producer(container, "桃子"), "生产者1").start();
        new Thread(new Consumer(container), "消费者1").start();
        new Thread(new Consumer(container), "消费者2").start();
        new Thread(new Producer(container, "雪梨"), "生产者2").start();

    }


}
