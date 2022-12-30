package com.cczu.java.jvm;

public class Demo1 {
    public static void main(String[] args) throws InterruptedException {
        /*
        -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

         */
        // eden 80m s0、s1 20 m old 100m
        Thread.sleep(30000);
        while (true){
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100*1024];
        }
        data = null;
        // 模拟一秒产生发生50次请求，共生成5m对象
        Thread.sleep(1000);
    }
}
