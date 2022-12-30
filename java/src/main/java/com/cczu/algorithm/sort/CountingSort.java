package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 计数排序
 *
 * @author jianzhen.yin
 * @date 2020/11/19
 */
public class CountingSort {
    /**
     * 如果要排序的数据有 n 个，我们把它们均匀地划分到 m 个桶内，每个桶里就有 k=n/m 个元素。每个桶内部使用快速排序，时间复杂度为 O(k * logk)。m 个桶排序的时间复杂度就是 O(m * k * logk)，
     * 因为 k=n/m，所以整个桶排序的时间复杂度就是 O(n*log(n/m))。当桶的个数 m 接近数据个数 n 时，log(n/m) 就是一个非常小的常量，
     * 这个时候桶排序的时间复杂度接近 O(n)。
     * <p>
     * 想弄明白这个问题，我们就要来看计数排序算法的实现方法。我还拿考生那个例子来解释。为了方便说明，我对数据规模做了简化。
     * 假设只有 8 个考生，分数在 0 到 5 分之间。这 8 个考生的成绩我们放在一个数组 A[8]中，它们分别是：2，5，3，0，2，3，0，3。
     * <p>
     * 思路是这样的：我们对 C[6]数组顺序求和，C[6]存储的数据就变成了下面这样子。C[k]里存储小于等于分数 k 的考生个数。
     * 我们从后到前依次扫描数组 A。比如，当扫描到 3 时，我们可以从数组 C 中取出下标为 3 的值 7，也就是说，到目前为止，
     * 包括自己在内，分数小于等于 3 的考生有 7 个，也就是说 3 是数组 R 中的第 7 个元素（也就是数组 R 中下标为 6 的位置）。
     * 当 3 放入到数组 R 中后，小于等于 3 的元素就只剩下了 6 个了，所以相应的 C[3]要减 1，变成 6。
     * <p>
     * 我总结一下，计数排序只能用在数据范围不大的场景中，如果数据范围 k 比要排序的数据 n 大很多，
     * 就不适合用计数排序了。而且，计数排序只能给非负整数排序，如果要排序的数据是其他类型的，要将其在不改变相对大小的情况下
     * ，转化为非负整数。比如，还是拿考生这个例子。如果考生成绩精确到小数后一位，我们就需要将所有的分数都先乘以 10，转化成整数，
     * 然后再放到 9010 个桶内。再比如，如果要排序的数据中有负数，数据的范围是[-1000, 1000]，
     * 那我们就需要先对每个数据都加 1000，转化成非负整数。
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {2, 5, 3, 0, 2, 3, 0, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        //求arr中最大的值
        int max = arr[0];
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 申请一个arr[0..max]的数组
        int[] tmp = new int[max + 1];
        // 将每个元素加入tmp下
        for (int i = 0; i < len; i++) {
            tmp[arr[i]]++;
        }
        // 依次累加
        for (int i = 1; i <= max; i++) {
            tmp[i] += tmp[i - 1];
        }
        // 临时数组r,存放排序后的结果
        int[] r = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            int cur = tmp[arr[i]];
            r[cur - 1] = arr[i];
            tmp[arr[i]]--;
        }
        // 拷贝到原数组
        System.arraycopy(r, 0, arr, 0, len);
    }
}
