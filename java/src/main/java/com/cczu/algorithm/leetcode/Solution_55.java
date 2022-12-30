package com.cczu.algorithm.leetcode;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.
 * <p>
 * Return true if you can reach the last index, or false otherwise.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 *
 * @author yjz
 * @date 2022/3/23
 */
public class Solution_55 {

    public boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前索引的位置超过了 上一个索引位置最大可达的距离 则不可达
            if (i > reachable) {
                return false;
            }
            // 当前位置最大可达位置
            reachable = Math.max(reachable,i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        Solution_55 solution_55 = new Solution_55();
        int[] tmp = {1,2,3,4,5};
        System.out.println(solution_55.canJump(tmp));
    }
}
