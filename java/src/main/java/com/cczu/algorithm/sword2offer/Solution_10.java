package com.cczu.algorithm.sword2offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 010. 和为 k 的子数组
 * 给定一个整数数组和一个整数 k ，请找到该数组中和为 k 的连续子数组的个数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2
 * 解释: 此题 [1,1] 与 [1,1] 为两种不同的情况
 * 示例 2：
 * <p>
 * 输入:nums = [1,2,3], k = 3
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class Solution_10 {
    public int subarraySum(int[] nums, int k) {
        /* 前缀和 + 哈希表优化  （key存放前缀和，value存放个数）
         * 演算公式：
         * 1. pre[i] = pre[i - 1] + nums[i];
         * 2. 假定[j,i]范围的和为k, 则 pre[j - 1] = pre[i] - k;
         * 所以要计算下标i的符合条件的个数，只需 计算 pre[i] - k 的 pre[j - 1]个数即可
         */
        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int preNum = 0;
        for (int num : nums) {
            preNum += num;
            result += map.getOrDefault(preNum - k, 0);
            map.put(preNum, map.getOrDefault(preNum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_10 solution_10 = new Solution_10();
        int[] arr = {5, 1, 2, 3};
        System.out.println(solution_10.subarraySum(arr, 3));
    }
}
