package com.cczu.algorithm.leetcode;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 * <p>
 * The overall run time complexity should be O(log (m+n)).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 * Example 2:
 * <p>
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * @author yjz
 * @date 2021/12/21
 */
public class Solution_4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4, 5, 6};
        Solution_4 solution4 = new Solution_4();
        System.out.println(solution4.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid = (nums1.length + nums2.length + 1) / 2;
        double res = getKth(nums1, nums2, mid);
        if ((nums1.length + nums2.length) % 2 == 0) {
            res += getKth(nums1, nums2, (nums1.length + nums2.length + 2) / 2);
            res = res / 2;
        }
        return res;
    }

    private double getKth(int[] nums1, int[] nums2, int mid) {
        if (nums1.length > nums2.length) {
            return getKth(nums2, nums1, mid);
        }
        if (nums1.length == 0) {
            return nums2[mid - 1];
        }
        if (mid == 1) {
            return Math.min(nums1[0], nums2[0]);
        }
        // 找出mid/2区间的数组
        // 关键思路，保证 x + y = mid   太绝了... 如果num1[x- 1] <  num2[y-1] ，则取左边大的部分，并且取mid值 为 mid - x
        int x = Math.min(nums1.length, mid / 2);
        int y = mid - x;
        if (nums1[x - 1] < nums2[y - 1]) {
            int[] newA = new int[nums1.length - x];
            System.arraycopy(nums1, x, newA, 0, nums1.length - x);
            return getKth(newA, nums2, y);
        } else {
            int[] newB = new int[nums2.length - y];
            System.arraycopy(nums2, y, newB, 0, nums2.length - y);
            return getKth(nums1, newB, x);
        }

    }
}
