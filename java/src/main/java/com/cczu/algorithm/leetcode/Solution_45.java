package com.cczu.algorithm.leetcode;

/**
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Your goal is to reach the last index in the minimum number of jumps.
 * <p>
 * You can assume that you can always reach the last index.
 * Example 1:
 * <p>
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 * <p>
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 * @author yjz
 * @date 2022/2/27
 */
public class Solution_45 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        Solution_45 solution_45 = new Solution_45();
        System.out.println(solution_45.jump(nums));
    }

    public int jump(int[] nums) {
        // 当前跳跃次数  You can assume that you can always reach the last index. 一定可达
        int stepCount = 0;
        // 上一次跳跃后最大可达的距离
        int preMaxStep = 0;
        // 当前跳跃后最大可达的距离
        int curMaxStep = 0;
        // loop 去掉最后一个元素
        for (int i = 0; i < nums.length - 1; i++) {
            curMaxStep = Math.max(curMaxStep, i + nums[i]);
            // 如果已经达到了上一次最大可达到的距离，则进行更新
            if (i == preMaxStep) {
                stepCount++;
                preMaxStep = curMaxStep;
            }
        }
        return stepCount;
    }
}
