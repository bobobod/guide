package com.cczu.java.jvm;

public class TestFullGC {
    public static void main(String[] args) {
        /*
        eden 8mb survivor 1mb maxObj 3mb old 10mb
-XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

  */

        byte[] array1 = new byte[4*1024 * 1024];
        array1=null;
        byte[] array2 = new byte[2*1024* 1024];
        byte[] array3 = new byte[2*1024* 1024];
        byte[] array4 = new byte[2*1024* 1024];
        byte[] array5 = new byte[128* 1024];
        byte[] array6 = new byte[2*1024* 1024];




    }
}
