package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 最小时间复杂度是O（n）
 * 平均最差时间复杂度是O（n2）
 * 插入排序相交冒泡排序有利的是 冒泡排序在比较交换时需要进行3次操作 而插入排序只要一次
 * 4，5，6，3，2，1
 * 有序度就是（4，5）（4，6）（5，6） 为3
 * 满有序度是n*（n-1）/2 = 15
 * 逆序度为 15-3 = 12
 * 插入排序共需插入次数是12次
 * @author jianzhen.yin
 * @date 2020/11/17
 */
public class InsertSort {
    private static void sort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            int j = i - 1;
            for (; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            arr[j + 1] = arr[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,1,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
