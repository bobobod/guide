package com.cczu.java.concurrent.deadlock;


public class DeadLock03 {
    public static void main(String[] args) {
        Account a = new Account(1,10000);
        Account b = new Account(2,1000);
        a.transfer(b, 100);
        b.transfer(a, 100);
    }


    static class Account {
        private int id;
        private int balance;

        public Account(int id, int balance) {
            this.id = id;
            this.balance = balance;
        }
        void transfer(Account to, int amt) {
            Account left = this;
            Account right = to;
            if (this.id > to.id) {
                left = to;
                right = left;
            }
            synchronized (left) {
                System.out.println(this.toString() + "lock obj1");
                synchronized (right) {
                    System.out.println(this.toString() + "lock obj2");
                    if (this.balance > amt) {
                        this.balance -= amt;
                        to.balance += amt;
                    }
                }
            }

        }
    }
}
