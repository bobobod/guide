package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度都是o(n2)
 * @author jianzhen.yin
 * @date 2020/11/17
 */
public class SelectSort {
    private static void sort(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int cur = i;
            for (int j = i+1; j < len; j++) {
                if (arr[j] < arr[cur]){
                    cur = j;
                }
            }
            if (cur != i){
                int tmp = arr[cur];
                arr[cur] = arr[i];
                arr[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,2,1,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
