package com.cczu.java.jvm;

public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        /*
        -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:InitialHeapSize=209715200 -XX:MaxHeapSize=209715200 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

         */
        // eden 80m s0、s1 20 m old 100m 大对象20m

        // young gc 回收的时间比 old gc还长，这个程序，每次full gc都是由young gc 触发的，因为young gc过后存活对象他太多要进入老年代了
        // 老年代内存不够才发生full gc，所以必须等待full gc结束后，才可以把young gc存活下来的对象放入old 区

        // 调整后
        // 堆：300m eden：100 s0、s1 50
        /*
                -XX:NewSize=104857600 -XX:MaxNewSize=104857600 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=20971520 -XX:InitialHeapSize=314572800 -XX:MaxHeapSize=314572800 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

         */
        Thread.sleep(30000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 4; i++) {
            data = new byte[10 * 1024 * 1024];
        }
        data = null;

        byte[] data1 = new byte[10 * 1024 * 1024];
        byte[] data2 = new byte[10 * 1024 * 1024];
        byte[] data3 = new byte[10 * 1024 * 1024];
        data3 = new byte[10 * 1024 * 1024];
        Thread.sleep(1000);
    }
}
