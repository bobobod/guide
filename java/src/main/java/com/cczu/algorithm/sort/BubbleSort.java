package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 最小时间复杂度是O（n）
 * 平均最差时间复杂度是O（n2）
 * 满有序度=有序度+逆序度
 * 4，5，6，3，2，1
 * 有序度就是（4，5）（4，6）（5，6） 为3
 * 满有序度是n*（n-1）/2 = 15
 * 逆序度为 15-3 = 12
 * 冒泡排序共需交换次数是12次
 *
 * @author jianzhen.yin
 * @date 2020/11/16
 */
public class BubbleSort {
    private static void sort(int[] arr) {
        int len = arr.length;
        for (int i = 0; i < len; i++) {
            boolean flag = false;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,1,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
