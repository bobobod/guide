package com.cczu.algorithm.leetcode;

import java.util.Arrays;

/**
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * <p>
 * Return the sum of the three integers.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * Example 2:
 * <p>
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 *
 * @author yjz
 * @date 2022/2/13
 */
public class Solution_16 {

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        Solution_16 solution_16 = new Solution_16();
        System.out.println(solution_16.threeSumClosest(nums, 1));
    }

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestNum = nums[0] + nums[1] + nums[nums.length - 1];
        for (int i = 0; i < nums.length - 2; i++) {
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                int currSum = nums[i] + nums[low] + nums[high];
                if (currSum > target) {
                    high--;
                } else {
                    low++;
                }
                if (Math.abs(currSum - target) < Math.abs(closestNum - target)) {
                    closestNum = currSum;
                }
            }
        }
        return closestNum;
    }
}
