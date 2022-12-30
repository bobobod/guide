package com.cczu.algorithm.sword2offerV2;

/**
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 * <p>
 * <p>
 * 限制：
 * <p>
 * 2 <= n <= 100000
 * <p>
 * 通过次数620,491提交次数914,287
 */
public class Solution_01 {
    public static void main(String[] args) {
        Solution_01 solution_01 = new Solution_01();

        int[] arr = {2,3,1,0,2,5,3};
        System.out.println(solution_01.findRepeatNumber(arr));
    }

    public int findRepeatNumber(int[] nums) {
        // 原地交换,找到第一个重复的元素
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i]) {
                continue;
            }
            if (nums[i] == nums[nums[i]]) {
                return nums[i];
            }
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
            i--;
        }
        return -1;
    }

}
