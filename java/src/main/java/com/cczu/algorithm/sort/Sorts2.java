package com.cczu.algorithm.sort;

import java.util.Arrays;

public class Sorts2 {
    /**
     * 冒泡排序
     *
     * @param arr
     */
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
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

    /**
     * 选择排序
     *
     * @param arr
     */
    private static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int cur = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[cur]) {
                    cur = j;
                }
            }
            if (cur != i) {
                int tmp = arr[i];
                arr[i] = arr[cur];
                arr[cur] = tmp;
            }
        }
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    private static void quickSort(int[] arr) {
        quickSort2(arr, 0, arr.length - 1);
    }

    private static void quickSort2(int[] arr, int start, int end) {
        if (start >= end) {
            // 终止条件
            return;
        }
        int p = partition(arr, start, end);
        quickSort2(arr, start, p - 1);
        quickSort2(arr, p + 1, end);
    }

    private static int partition(int[] arr, int start, int end) {

        int pivot = arr[end];
        int cur = start;
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                if (cur != i) {
                    int tmp = arr[cur];
                    arr[cur] = arr[i];
                    arr[i] = tmp;
                }
                cur++;
            }
        }
        arr[end] = arr[cur];
        arr[cur] = pivot;
        return cur;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 0, 2, 5};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr2 = {1, 3, 0, 2, 5};
        quickSort(arr2);
        System.out.println(Arrays.toString(arr2));
        int[] arr3 = {1, 3, 0, 2, 5};
        selectSort(arr3);
        System.out.println(Arrays.toString(arr3));
    }

}
