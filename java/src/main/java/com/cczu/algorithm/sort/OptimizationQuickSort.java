package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 优化快速排序算法
 *
 * @author jianzhen.yin
 * @date 2020/11/25
 */
public class OptimizationQuickSort {
    private static void sort(int[] arr) {
        quicksort(arr, 0, arr.length - 1);
    }

    private static void quicksort(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        // 获取分区点
        int q = partition(arr, p, r);
        quicksort(arr, p, q - 1);
        quicksort(arr, q + 1, r);
    }

    private static int partition(int[] arr, int left, int right) {
        // 三数取中法
        int middle = left + ((right - left) >> 1);
        int pivot = arr[middle];
        //交换到最后面
        arr[middle] = arr[right];
        arr[right] = pivot;
        // 这部分较难理解，得细品
        int i = left;
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
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

    /**
     * 递推公式
     * quicksort(p..r) = quicksort(p..q) + quicksort(q+1..r)
     * p>=r
     * <p>
     * // 快速排序，A是数组，n表示数组的大小
     * quick_sort(A, n) {
     * quick_sort_c(A, 0, n-1)
     * }
     * // 快速排序递归函数，p,r为下标
     * quick_sort_c(A, p, r) {
     * if p >= r then return
     * <p>
     * q = partition(A, p, r) // 获取分区点
     * quick_sort_c(A, p, q-1)
     * quick_sort_c(A, q+1, r)
     * 因为分区的过程涉及交换操作，如果数组中有两个相同的元素，比如序列 6，8，7，6，3，5，9，4，在经过第一次分区操作之后，两个 6 的相对先后顺序就会改变。所以，快速排序并不是一个稳定的排序算法。
     * 快速排序是原地不稳定的排序算法
     * 快速排序算法虽然最坏情况下的时间复杂度是 O(n2)，但是最小平均情况下时间复杂度都是 O(nlogn)。不仅如此，快速排序算法时间复杂度退化到 O(n2) 的概率非常小，我们可以通过合理地选择 pivot 来避免这种情况。
     * }
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 4, 1, 5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
