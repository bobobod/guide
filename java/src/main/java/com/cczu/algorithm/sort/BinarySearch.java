package com.cczu.algorithm.sort;

/**
 * 二分查找
 *
 * @author jianzhen.yin
 * @date 2020/11/25
 */
public class BinarySearch {
    /**
     * 我们假设数据大小是 n，每次查找后数据都会缩小为原来的一半，也就是会除以 2。最坏情况下，直到查找区间被缩小为空，才停止。可以看出来，这是一个等比数列。
     * 其中 n/2k=1 时，k 的值就是总共缩小的次数。而每一次缩小操作只涉及两个数据的大小比较，所以，经过了 k 次区间缩小操作，时间复杂度就是 O(k)。
     * 通过 n/2k=1，我们可以求得 k=log2n，所以时间复杂度就是 O(logn)。
     *
     * 1.二分查找依赖的是顺序表结构，简单点说就是数组。
     * 2.其次，二分查找针对的是有序数据。
     * 所以，二分查找只能用在插入、删除操作不频繁，一次排序多次查找的场景中。针对动态变化的数据集合，二分查找将不再适用
     * 3.再次，数据量太小不适合二分查找。
     * 二分查找的底层需要依赖数组这种数据结构，而数组为了支持随机访问的特性，要求内存空间连续，对内存的要求比较苛刻。比如，我们有 1GB 大小的数据，如果希望用数组来存储，
     * 那就需要 1GB 的连续内存空间。注意这里的“连续”二字，也就是说，即便有 2GB 的内存空间剩余，但是如果这剩余的 2GB 内存空间都是零散的，没有连续的 1GB 大小的内存空间，
     * 那照样无法申请一个 1GB 大小的数组。而我们的二分查找是作用在数组这种数据结构之上的，所以太大的数据用数组存储就比较吃力了，也就不能用二分查找了
     *
     *
     * 二分查找虽然性能比较优秀，但应用场景也比较有限。底层必须依赖数组，并且还要求数据是有序的。对于较小规模的数据查找，我们直接使用顺序遍历就可以了，二分查找的优势并不明显。
     * 二分查找更适合处理静态数据，也就是没有频繁的数据插入、删除操作。
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(bSearch(arr, 2));
    }

    public static int bSearch(int[] arr, int value) {
//        return recursiveSearch(arr, 0, arr.length - 1, value);
        return nonRecursiveSearch(arr, value);
    }

    private static int recursiveSearch(int[] arr, int left, int right, int value) {
        if (left > right) {
            return -1;
        }
        // 注意点 left+right的和可能大于int的范围
        //实际上，mid=(low+high)/2 这种写法是有问题的。因为如果 low 和 high 比较大的话，两者之和就有可能会溢出。
        // 改进的方法是将 mid 的计算方式写成 low+(high-low)/2。
        // 更进一步，如果要将性能优化到极致的话，我们可以将这里的除以 2 操作转化成位运算 low+((high-low)>>1)。因为相比除法运算来说，计算机处理位运算要快得多。
//        int middle = (left + right) / 2;
        int middle = left + ((right - left) >> 1);
        if (value > arr[middle]) {
            return recursiveSearch(arr, middle + 1, right, value);
        } else if (value < arr[middle]) {
            return recursiveSearch(arr, left, middle - 1, value);
        } else {
            return middle;
        }

    }

    private static int nonRecursiveSearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            if (value == arr[middle]) {
                return middle;
            } else if (value > arr[middle]) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return -1;
    }
}
