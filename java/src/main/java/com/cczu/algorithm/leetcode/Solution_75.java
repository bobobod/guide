package com.cczu.algorithm.leetcode;

/**
 * 75. Sort Colors
 * Medium
 * <p>
 * 9459
 * <p>
 * 408
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.
 * <p>
 * We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
 * <p>
 * You must solve this problem without using the library's sort function.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * Example 2:
 * <p>
 * Input: nums = [2,0,1]
 * Output: [0,1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] is either 0, 1, or 2.
 * <p>
 * <p>
 * Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 *
 * @author yjz
 * @date 2022/4/18
 */
public class Solution_75 {
    public static void main(String[] args) {
        Solution_75 solution_75 = new Solution_75();
        int[] nums = {2, 1, 1, 0, 2};
        solution_75.sortColors(nums);
        for (int so : nums) {
            System.out.println(so);
        }
    }

    public void sortColors(int[] nums) {
        /*
         * minCur 当前最小值的索引
         * maxCur    当前最大值的索引
         * current  当前游标
         */
        int minCur = 0, maxCur = nums.length - 1, current = 0;
        while (current <= maxCur) {
            if (nums[current] == 0) {
                // 最小值
                swap(nums, minCur, current);
                minCur++;
            }
            if (nums[current] == 2) {
                // 最大值
                swap(nums, maxCur, current);
                maxCur--;
                // 当前索引回退一步
                current--;
            }
            current++;
        }

    }

    public void swap(int[] nums, int start, int end) {
        int tmp = nums[start];
        nums[start] = nums[end];
        nums[end] = tmp;
    }
}
