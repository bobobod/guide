package com.cczu.java.jvm;

public class TestYoungGC2 {
    public static void main(String[] args) {
        /*
-XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

  */
        // 第二次gc时，survivor区的内存大小对象高于50%，直接把超过50%的年龄对象都晋升到老年代
        byte[] array1 = new byte[2*1024 * 1024];
        array1 = new byte[2*1024 * 1024];
        array1 = new byte[2*1024 * 1024];
        array1=null;
        byte[] array2 = new byte[128* 1024];
        // 第一次 minor gc
        byte[] array3 = new byte[2*1024* 1024];
        array3 = new byte[2*1024* 1024];
        array3 = new byte[2*1024* 1024];
        array3 = new byte[128* 1024];
        array3 = null;
        // 第二次minor gc
        byte[] array4 = new byte[2*1024* 1024];


    }
}
