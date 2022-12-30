package com.cczu.java.jvm;

public class TestYoungGC3 {
    public static void main(String[] args) {
                /*
        eden 8mb survivor 1mb maxObj 3mb old 10mb
-XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

  */
        // 在这种场景下 会有部分对象会留在survivor区，有部分对象进入老年代
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        byte[] array2 = new byte[128 * 1024];
//        byte[] array2 = new byte[2*1024 * 1024];
        array2 = null;
        byte[] array3 = new byte[2 * 1024 * 1024];


    }
}
