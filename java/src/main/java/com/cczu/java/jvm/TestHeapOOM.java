package com.cczu.java.jvm;

import java.util.ArrayList;

public class TestHeapOOM {
    public static void main(String[] args) {
        // 模拟堆内存溢出
        // -Xms10m -Xmx10m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
        ArrayList<Object> obj = new ArrayList<>();
        while (true){
            obj.add(new Object());
        }
    }
}
