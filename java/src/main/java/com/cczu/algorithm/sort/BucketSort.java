package com.cczu.algorithm.sort;


import java.util.Arrays;

/**
 * 桶排序算法
 *
 * @author jianzhen.yin
 * @date 2020/11/23
 */
public class BucketSort {
    /**
     * 如果要排序的数据有 n 个，我们把它们均匀地划分到 m 个桶内，每个桶里就有 k=n/m 个元素。每个桶内部使用快速排序，
     * 时间复杂度为 O(k * logk)。m 个桶排序的时间复杂度就是 O(m * k * logk)，因为 k=n/m，所以整个桶排序的时间复杂度就是 O(n*log(n/m))。
     * 当桶的个数 m 接近数据个数 n 时，log(n/m) 就是一个非常小的常量，这个时候桶排序的时间复杂度接近 O(n)。
     * <p>
     * 我们可以先扫描一遍文件，看订单金额所处的数据范围。假设经过扫描之后我们得到，
     * 订单金额最小是 1 元，最大是 10 万元。我们将所有订单根据金额划分到 100 个桶里，
     * 第一个桶我们存储金额在 1 元到 1000 元之内的订单
     * ，第二桶存储金额在 1001 元到 2000 元之内的订单，以此类推。每一个桶对应一个文件，
     * 并且按照金额范围的大小顺序编号命名（00，01，02…99）。理想的情况下，如果订单金额在 1 到 10 万之间均匀分布
     * ，那订单会被均匀划分到 100 个文件中，每个小文件中存储大约 100MB 的订单数据，
     * 我们就可以将这 100 个小文件依次放到内存中，用快排来排序。等所有文件都排好序之后，我们只需要按照文件编号，
     * 从小到大依次读取每个小文件中的订单数据，并将其写入到一个文件中，那这个文件中存储的就是按照金额从小到大排序的订单数据了。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3, 1, 1, 1, 3, 3, 9, 2, 10};
        sort(arr);
        System.out.println(Arrays.toString(arr));

        int[][] ints = new int[2][3];
        ints[0] = new int[5];
        System.out.println("----");
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                System.out.print(ints[i][j]+ "  ");
            }
            System.out.println();
        }
    }

    public static void sort(int[] arr) {
        bucketSort(arr, 2);
    }

    private static void bucketSort(int[] arr, int capacity) {
        if (arr.length < 2) {
            return;
        }
        int minVal = arr[0];
        int maxVal = arr[1];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minVal) {
                minVal = arr[i];
            }
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        int bucketNum = (maxVal - minVal) / capacity + 1;
        int[][] buckets = new int[bucketNum][capacity];
        int[] indexArr = new int[bucketNum];
        // 将数组中每个值放入桶中
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - minVal) / capacity;
            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }
        // 对每个桶排序
        int k = 0;
        for (int i = 0; i < bucketNum; i++) {
            // 无元素直接跳过
            if (indexArr[i] == 0) {
                continue;
            }
            quickSortC(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[k++] = buckets[i][j];
            }
        }
    }

    public static void quickSortC(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        int q = partition(arr, p, r);
        quickSortC(arr, p, q - 1);
        quickSortC(arr, q + 1, r);
    }

    private static int partition(int[] arr, int p, int r) {
        int pivot = arr[r];
        int i = p;
        // 注意，这里比较到r-1个元素即可，不要在比较基准元素
        for (int j = p; j < r; j++) {
            if (pivot >= arr[j]) {
                swap(arr, i, j);
                i++;
            }
        }
        // 和基准值交换元素
        swap(arr, i, r);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        System.out.println("扩容了。。。");
        int[] oldBucket = buckets[bucketIndex];
        int[] newBucket = new int[oldBucket.length * 2];
        System.arraycopy(oldBucket, 0, newBucket, 0, oldBucket.length);
        buckets[bucketIndex] = newBucket;
    }


}
