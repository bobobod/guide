package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 009. 乘积小于 K 的子数组
 * 给定一个正整数数组 nums和整数 k ，请找出该数组内乘积小于 k 的连续的子数组的个数。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [10,5,2,6], k = 100
 * 输出: 8
 * 解释: 8 个乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
 * 示例 2:
 * <p>
 * 输入: nums = [1,2,3], k = 0
 * 输出: 0
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class Solution_09 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;
        int start = 0, end = 0;
        int multiply = 1;
        while (end < nums.length) {
            multiply *= nums[end];
            while (start <= end && multiply >= k) {
                multiply /= nums[start];
                start++;
            }
            result += end - start + 1;
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_09 solution_09 = new Solution_09();
        int[] arr = {10, 5, 2, 6};
        System.out.println(solution_09.numSubarrayProductLessThanK(arr, 100));
    }
}
