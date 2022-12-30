package com.cczu.algorithm.sort;

/**
 * 变形二分查找
 *
 * @author jianzhen.yin
 * @date 2020/11/26
 */
public class DeformationBinarySearch {
    /**
     * 变形问题
     * 1. 查找第一个和给定值相等的元素
     * 2. 查看最后一个和给定值相等的元素
     * 3. 查找第一个大于等于给定值的元素
     * 4. 查找最后一个小于等于给定值的元素
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 5, 7};
        System.out.println(nonRecursiveSearch1(arr, 2));
        System.out.println(nonRecursiveSearch2(arr, 2));
        System.out.println(nonRecursiveSearch3(arr, 4));
        System.out.println(nonRecursiveSearch4(arr, 4));
    }

    /**
     * 问题1
     *
     * @param arr
     * @param value
     * @return
     */
    private static int nonRecursiveSearch1(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < arr[middle]) {
                right = middle - 1;
            } else if (value > arr[middle]) {
                left = middle + 1;
            } else {
                // 如果是第一个元素或前面不存在相等的元素则直接返回
                if (middle == 0 || arr[middle - 1] != value) {
                    return middle;
                }
                right = middle - 1;
            }
        }
        return -1;
    }

    /**
     * 问题2
     *
     * @param arr
     * @param value
     * @return
     */
    private static int nonRecursiveSearch2(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < arr[middle]) {
                right = middle - 1;
            } else if (value > arr[middle]) {
                left = middle + 1;
            } else {
                // 如果是最后一个元素或后面不存在相等的元素则直接返回
                if (middle == arr.length - 1 || arr[middle + 1] != value) {
                    return middle;
                }
                left = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 问题3
     *
     * @param arr
     * @param value
     * @return
     */
    private static int nonRecursiveSearch3(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < arr[middle]) {
                if (middle == 0 || arr[middle - 1] < value) {
                    return middle;
                }
                right = middle - 1;
            } else if (value > arr[middle]) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 问题4
     *
     * @param arr
     * @param value
     * @return
     */
    private static int nonRecursiveSearch4(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value < arr[middle]) {
                right = middle - 1;
            } else if (value > arr[middle]) {
                if (middle == arr.length - 1 || arr[middle + 1] > value) {
                    return middle;
                }
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

}
