package com.cczu.java.jvm;

public class TestStackOOM {
    public static void main(String[] args) {
        // 模拟栈内存溢出
        // -XX:ThreadStackSize=1m
        print();
    }
    public static void print(){
        System.out.println("hello");
        print();
    }
}
