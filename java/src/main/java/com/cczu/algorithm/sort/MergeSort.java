package com.cczu.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 平均情况和最坏情况都是nLog(n)
 *
 *1.在合并的过程中，如果 A[p…q]和 A[q+1…r]之间有值相同的元素，那我们可以像伪代码中那样，
 * 先把 A[p…q]中的元素放入 tmp 数组。这样就保证了值相同的元素，在合并前后的先后顺序不变。所以，归并排序是一个稳定的排序算法。
 *
 * 归并排序不适用的原因在于归并排序不是原地排序算法，需要O（n）的空间复杂度
 * @author jianzhen.yin
 * @date 2020/11/18
 */
public class MergeSort {
    private static void sort(int[] arr) {
        mergeC(arr, 0, arr.length - 1);
    }

    private static void mergeC(int[] arr, int p, int r) {
        if (p >= r) {
            return;
        }
        // 防止p+r超过int大小
        int q = (r - p) / 2 + p;
        //分治递归
        mergeC(arr, p, q);
        mergeC(arr, q + 1, r);
        // 将arr[p,q]和arr[q+1,r]合并到arr[p,r]中
        mergeInternal(arr, p, q, r);
    }

    private static void mergeInternal(int[] arr, int p, int q, int r) {
        // 使用临时数组存放数据,数组大小为r-p+1
        int[] tmp = new int[r - p + 1];
        int i = p, j = q + 1, k = 0;
        while (i <= q && j <= r) {
            if (arr[i] <= arr[j]) {
                tmp[k++] = arr[i++];
            } else {
                tmp[k++] = arr[j++];
            }
        }
        // 取最后剩余的元素
        int start = i;
        int end = q;
        if (j <= r) {
            start = j;
            end = r;
        }
        while (start <= end) {
            tmp[k++] = arr[start++];
        }
        //将tmp数组的数据拷贝回原数组
        if (r - p + 1 >= 0) {
            System.arraycopy(tmp, 0, arr, p, r - p + 1);
        }
    }

    /**
     *
     递推公式：
     merge_sort(p…r) = merge(merge_sort(p…q), merge_sort(q+1…r))

     终止条件：
     p >= r 不用再继续分解

     *时间复杂度计算
     * 我们假设对 n 个元素进行归并排序需要的时间是 T(n)，那分解成两个子数组排序的时间都是 T(n/2)。我们知道，merge() 函数合并两个有序子数组的时间复杂度是 O(n)。所以，套用前面的公式，归并排序的时间复杂度的计算公式就是：
     * T(1) = C；   n=1时，只需要常量级的执行时间，所以表示为C。
     * T(n) = 2*T(n/2) + n； n>1
     *
     T(n) = 2*T(n/2) + n
     = 2*(2*T(n/4) + n/2) + n = 4*T(n/4) + 2*n
     = 4*(2*T(n/8) + n/4) + 2*n = 8*T(n/8) + 3*n
     = 8*(2*T(n/16) + n/8) + 3*n = 16*T(n/16) + 4*n
     ......
     = 2^k * T(n/2^k) + k * n
     ......
     通过这样一步一步分解推导，我们可以得到 T(n) = 2^kT(n/2^k)+kn。当 T(n/2^k)=T(1) 时，也就是 n/2^k=1，我们得到 k=log2n 。我们将 k 值代入上面的公式，得到 T(n)=Cn+nlog2n 。如果我们用大 O 标记法来表示的话，T(n) 就等于 O(nlogn)。所以归并排序的时间复杂度是 O(nlogn)。
     从我们的原理分析和伪代码可以看出，归并排序的执行效率与要排序的原始数组的有序程度无关，所以其时间复杂度是非常稳定的，不管是最好情况、最坏情况，还是平均情况，时间复杂度都是 O(nlogn)。
     因为它有一个致命的“弱点”，那就是归并排序不是原地排序算法。这是因为归并排序的合并函数，在合并两个有序数组为一个有序数组时，需要借助额外的存储空间。
     在任意时刻，CPU 只会有一个函数在执行，也就只会有一个临时的内存空间在使用。临时内存空间最大也不会超过 n 个数据的大小，所以空间复杂度是 O(n)。
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,2,1,5};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
