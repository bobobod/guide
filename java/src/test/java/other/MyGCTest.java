package other;

public class MyGCTest {
    public static void main(String[] args) {
        /*
         *  空跑程序约占6Mb, eden 40Mb  survivor 5mb  old 150mb  分代年龄 3  survivorRatio= 0.6*5 = 3mb
         *-verbose:gc -Xmx200M -Xms200M -Xmn50M -XX:+PrintGCDetails -XX:TargetSurvivorRatio=60 -XX:+PrintTenuringDistribution -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=3 -XX:+UseConcMarkSweepGC -XX:+UseParNewGC
         */
        // 空跑占用 5.6Mb 算 6mb
        byte[] byte1m_1 = new byte[1 * 1024 * 1024];
        byte[] byte1m_2 = new byte[1 * 1024 * 1024];
        byte[] byte1m_3 = new byte[1 * 1024 * 1024];
        // 使用函数方式来申请空间，函数运行完成后，就会被回收
        makeGarbage(31);
        byte[] byteArr = new byte[10 * 1024 * 1024];
        // 这次Minor Gc时, 三个1M的数组因为尚有引用，所以进入From区域（因为是第一次GC）age为1
        // 且由于From区已经占用达到了60%(-XX:TargetSurvivorRatio=60), 所以会重新计算对象晋升的age。
        // 计算方法见上文，计算出age：min(age, MaxTenuringThreshold) = 1，输出中会有Desired survivor size 3145728 bytes, new threshold 1 (max 3)字样
        //新的数组byteArr进入eden区域。

        //再次触发垃圾回收，证明三个1M的数组会因为其第二次回收后age为2，大于上一次计算出的new threshold 1，所以进入老年代。
        //而byteArr因为超过survivor的单个区域，直接进入了老年代。
        // 老年代的信息为 concurrent mark-sweep generation 和以前版本略有不同。另外，还列出了某次 GC 后是否重新生成了 threshold，以及各个年龄占用空间的大小
        makeGarbage(34);
    }

    private static void makeGarbage(int size) {
        byte[] byteArrTemp = new byte[size * 1024 * 1024];
    }
}
