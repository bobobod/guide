package com.cczu.java.concurrent.deadlock;

import java.util.ArrayList;
import java.util.List;

public class DeadLock01 {
    public static void main(String[] args) {
        Account a = new Account();
        Account b = new Account();
        a.transfer(b,100);
        b.transfer(a,100);
    }

    static class Allocator {
        private List<Object> als = new ArrayList<>();

        public synchronized boolean apply(Object from, Object to) {
            if (als.contains(from) || als.contains(to)) {
                return false;
            } else {
                als.add(from);
                als.add(to);
            }
            return true;
        }

        synchronized void clean(Object from, Object to) {
            als.remove(from);
            als.remove(to);
        }
    }

    private static class SingleTonHolder {
        private static Allocator INSTANCE = new Allocator();
    }

    public static Allocator getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    static class Account {
        private Allocator actr = DeadLock01.getInstance();

        private int balance;

        void transfer(Account to, int amt) {
            while (!actr.apply(this, to)) {
            }
            try {
                synchronized (this) {
                    System.out.println(this.toString() + "lock obj1");
                    synchronized (to) {
                        System.out.println(this.toString() + "lock obj2");
                        if (this.balance > amt) {
                            this.balance -= amt;
                            to.balance += amt;
                        }
                    }
                }
            } finally {
                actr.clean(this, to);
            }
        }
    }
}
