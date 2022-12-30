package com.cczu.algorithm.sword2offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 011. 0 和 1 个数相同的子数组
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。
 * 示例 2：
 * <p>
 * 输入: nums = [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * nums[i] 不是 0 就是 1
 */
public class Solution_11 {
    public int findMaxLength(int[] nums) {
        /*
         * 同solution_10解题思路
         * 前缀和
         *  1. 初始化map值（0,-1) 避免前两位就是最长数组长度的情况
         *  2. 声明前缀和变量 preNum = 0, ret = 0
         *  3. 遍历数组，若元素是0则换成-1，则变成求和为0的最长子数组的长度
         */
        int ret = 0;
        // (key存放前缀和，value存放索引）
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preNum = 0;
        for (int i = 0; i < nums.length; i++) {
            preNum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(preNum)) {
                ret = Math.max(ret, i - map.get(preNum));
            } else {
                map.put(preNum, i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 0};
        Solution_11 solution_11 = new Solution_11();
        System.out.println(solution_11.findMaxLength(arr));
    }
}
