package com.cczu.algorithm.sort;


import java.util.Arrays;

public class QuickSort2 {
    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 获取分区点
        int p = partition(arr, left, right);
        quickSort(arr, left, p - 1);
        quickSort(arr, p + 1, right);
    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    // 交换
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
                i++;
            }
        }
        arr[right] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static void main(String[] args) {
        QuickSort2 quickSort2 = new QuickSort2();
        int[] arr = {3, 2, 1, 5, 3, 6, 7, 2};
        quickSort2.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
