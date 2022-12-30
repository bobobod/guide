package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 * <p>
 * Notice that the solution set must not contain duplicate triplets.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Example 2:
 * <p>
 * Input: nums = []
 * Output: []
 * Example 3:
 * <p>
 * Input: nums = [0]
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * <p>
 * reference:https://www.code-recipe.com/post/three-sum
 *
 * @author yjz
 * @date 2022/2/12
 */
public class Solution_15 {
    public static void main(String[] args) {
        Solution_15 solution_15 = new Solution_15();
        int[] r = {0, 0, 0};
        System.out.println(solution_15.threeSum(r));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 左边去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int tmp = nums[i] + nums[j] + nums[k];
                if (tmp == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    k--;
                    // 右边去重
                    while (j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                } else if (tmp > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }


}
