package com.cczu.java.jvm;

public class TestYoungGC {
    public static void main(String[] args) {
        /*
-XX:NewSize=5242880 -XX:MaxNewSize=5242880 -XX:InitialHeapSize=10485760 -XX:MaxHeapSize=10485760 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:SurvivorRatio=8 -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log

  */

        byte[] array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1 = new byte[1024 * 1024];
        array1=null;
        byte[] array2 = new byte[2 * 1024 * 1024];
    }
}
