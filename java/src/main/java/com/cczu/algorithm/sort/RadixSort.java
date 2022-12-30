package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author jianzhen.yin
 * @date 2020/11/24
 */
public class RadixSort {
    /**
     * 根据每一位来排序，我们可以用刚讲过的桶排序或者计数排序，它们的时间复杂度可以做到 O(n)。如果要排序的数据有 k 位，
     * 那我们就需要 k 次桶排序或者计数排序，总的时间复杂度是 O(k*n)。当 k 不大的时候，比如手机号码排序的例子，
     * k 最大就是 11，所以基数排序的时间复杂度就近似于 O(n)。实际上，有时候要排序的数据并不都是等长的，比如我们排序牛津字典中的 20 万个英文单词，
     * 最短的只有 1 个字母，最长的我特意去查了下，有 45 个字母，中文翻译是尘肺病。对于这种不等长的数据，基数排序还适用吗？
     * 实际上，我们可以把所有的单词补齐到相同长度，位数不够的可以在后面补“0”，因为根据ASCII 值，所有字母都大于“0”，所以补“0”不会影响到原有的大小顺序。
     * 这样就可以继续用基数排序了。我来总结一下，基数排序对要排序的数据是有要求的，需要可以分割出独立的“位”来比较，而且位之间有递进的关系，如果 a 数据的高位比 b 数据大，
     * 那剩下的低位就不用比较了。除此之外，每一位的数据范围不能太大，要可以用线性排序算法来排序，否则，基数排序的时间复杂度就无法做到 O(n) 了。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3, 1, 1, 1, 3, 3, 9, 2, 10};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int maxVal = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }
        // 从个位开始对arr排序
        for (int i = 1; maxVal / i > 0; i *= 10) {
            countingSort(arr, i);
        }
    }

    private static void countingSort(int[] arr, int exp) {
        int[] tmp = new int[10];
        for (int i = 0; i < arr.length; i++) {
            tmp[(arr[i] / exp) % 10]++;
        }
        // 每个数字的位置
        for (int i = 1; i < tmp.length; i++) {
            tmp[i] += tmp[i - 1];
        }
        // 临时变量存放结果
        int[] r = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            // 当前数字位置-1表示下标
            r[tmp[(arr[i] / exp) % 10] - 1] = arr[i];
            tmp[(arr[i] / exp) % 10]--;
        }
        // 拷贝回原数组
        System.arraycopy(r, 0, arr, 0, r.length);
    }
}
