package com.cczu.designpattern.creation.singleton;

public class SingletonDemo {
    private static volatile SingletonDemo INSTANCE;

    private SingletonDemo() {
    }

    public static SingletonDemo getSingleton() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonDemo();
                }
            }
        }
        return INSTANCE;
    }

}
