package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 008. 和大于等于 target 的最短子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 * 示例 2：
 * <p>
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 * 示例 3：
 * <p>
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class Solution_08 {
    public int minSubArrayLen(int target, int[] nums) {
        /*
         * 滑动窗口解法：
         * 1. 最开始 start和end相等，等于0
         * 2. end+1，当sum >= target的时候，更新结果集
         * 3. start+1, sum减去nums[start],期间也更新最短数组长度，直到 sum < target, 再滑动end
         */
        int minSubArrLen = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;
        while (end < nums.length) {
            sum += nums[end];
            while (sum >= target) {
                minSubArrLen = Math.min(minSubArrLen, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return minSubArrLen == Integer.MAX_VALUE ? 0 : minSubArrLen;
    }

    public static void main(String[] args) {
        Solution_08 solution_08 = new Solution_08();
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(solution_08.minSubArrayLen(11, arr));
    }
}
