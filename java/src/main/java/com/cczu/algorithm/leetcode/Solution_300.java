package com.cczu.algorithm.leetcode;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 *
 * Example:
 *
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n2) complexity.
 * Follow up: Could you improve it to O(n log n) time complexity?
 *
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_300 {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]){
                    // 判断比nums[i]小的最大长度值
                    maxVal = Math.max(dp[j],maxVal);
                }
            }
            dp[i] = maxVal + 1;
            maxLen = Math.max(dp[i],maxLen);
        }
        return maxLen;
    }
}
