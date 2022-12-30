package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * @author jianzhen.yin
 * @date 2020/12/8
 */
public class HeapSort {
    /**
     * 堆排序是一种原地的、时间复杂度为 O(nlogn) 的排序算法
     * 堆，这里的堆不是java内存模型中的堆。
     * 堆的定义有两个：
     * 1.完全二叉树；
     * 2.每个节点都比左右子节点大，这时的堆叫“大顶堆“，当然也可以有”小顶堆” 堆结构的排序叫做堆排序。堆排序时间复杂度为nlogn，跟快速排序是一样的。
     * 堆排序是原地排序，不是稳定排序
     * <p>
     * 注意：对于完全二叉树来说，下标从 n/2+1 到 n 的都是叶子节点
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
        Heap heap = new Heap(8);
        heap.insert(1);
        heap.insert(2);
        heap.insert(3);
        heap.insert(4);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.print();
        heap.deleteMax();
        heap.print();

    }

    public static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int len = arr.length;
        // 1.建堆
        //从(len-1)/2开始堆化,由于这个是从下标为0开是计数的 左节点下标为2*i+1 右节点为2*i+2，原来考虑的是从下标为1开始计数的
        //也就是最后一个非叶子节点，依次堆化直到根节点
        // 为什么是从 (len - 1)/2 + 1 到 len - 1 都是叶子节点不需要去堆化
        for (int i = (len - 1) / 2; i >= 0; i--) {
            heapify(arr, len - 1, i);
        }
        for (int i = 0; i < len; i++) {
            System.out.println(arr[i] + " ");
        }
        // 2.排序
        int k = len - 1;
        while (k > 0) {
            // 把第一个元素和最后一个元素进行交换
            swap(arr, 0, k);
            // 重新堆化
            // 将剩下元素重新堆化，堆顶元素变成最大元素
            heapify(arr, --k, 0);
        }
    }

    /**
     * 堆化
     *
     * @param arr 数组
     * @param n   最后一个数组的下标
     * @param i   要堆化的下标
     */
    public static void heapify(int[] arr, int n, int i) {
        while (true) {
            // 最大值的位置
            int maxPos = i;
            if (i * 2 + 1 <= n && arr[i * 2 + 1] > arr[i]) {
                maxPos = i * 2 + 1;
            }
            if (i * 2 + 2 <= n && arr[maxPos] < arr[i * 2 + 2]) {
                maxPos = i * 2 + 2;
            }
            // 相等时跳出
            if (maxPos == i) {
                break;
            }
            swap(arr, i, maxPos);
            // 向子节点继续查找
            i = maxPos;
        }
    }

    public static void swap(int[] arr, int m, int n) {
        int tmp = arr[m];
        arr[m] = arr[n];
        arr[n] = tmp;
    }
}

class Heap {
    /**
     * 存放数据
     */
    private int[] arr;
    /**
     * 数组容量
     */
    private int capacity = 8;
    /**
     * 当前存储数量
     */
    private int count;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity + 1];
        this.count = 0;
    }

    public void insert(int data) {
        if (count >= capacity) {
            return;
        }
        // 存放完全二叉树时，浪费了第一个数据的空间
        ++count;
        arr[count] = data;
        int pos = count;
        while (pos / 2 > 0 && arr[pos / 2] < data) {
            swap(arr, pos / 2, pos);
            pos = pos / 2;
        }
    }

    public void deleteMax() {
        if (count == 0) {
            // 没有数据
            return;
        }
        // 把最大的位置数据替换成最后一个元素，再依次堆化
        arr[1] = arr[count];
        --count;
        heapify(arr, count, 1);
    }

    private void heapify(int[] arr, int count, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= count && arr[i * 2] > arr[i]) {
                maxPos = i * 2;
            }
            if (i * 2 + 1 <= count && arr[maxPos] < arr[i * 2 + 1]) {
                maxPos = i * 2 + 1;
            }
            // 当前值就是最大值
            if (maxPos == i) {
                break;
            }
            swap(arr, maxPos, i);
            i = maxPos;
        }
    }

    public void swap(int[] arr, int m, int n) {
        int tmp = arr[m];
        arr[m] = arr[n];
        arr[n] = tmp;
    }

    public void print() {
        for (int i = 1; i <= count; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}