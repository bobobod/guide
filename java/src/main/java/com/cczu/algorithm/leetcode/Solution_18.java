package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * <p>
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 *
 * @author yjz
 * @date 2022/2/15
 */
public class Solution_18 {
    public static void main(String[] args) {
        int[] arr = {1, 0, -1, 0, -2, 2};
        Solution_18 solution_18 = new Solution_18();
        System.out.println(solution_18.fourSum(arr, 0));
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) {
            return result;
        }

        for (int num1Idx = 0; num1Idx + 3 < nums.length; num1Idx++) {
            // 去除重复数据
            if (num1Idx > 0 && nums[num1Idx - 1] == nums[num1Idx]) {
                continue;
            }
            for (int num2Idx = num1Idx + 1; num2Idx + 2 < nums.length; num2Idx++) {
                // 去除重复数据
                if (num2Idx > num1Idx + 1 && nums[num2Idx - 1] == nums[num2Idx]) {
                    continue;
                }
                int num3Idx = num2Idx + 1;
                int num4Idx = nums.length - 1;
                while (num3Idx < num4Idx) {
                    int sum = nums[num1Idx] + nums[num2Idx] + nums[num3Idx] + nums[num4Idx];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[num1Idx], nums[num2Idx], nums[num3Idx], nums[num4Idx]));
                        num4Idx--;
                        num3Idx++;
                        // 去除重复数据
                        while (num3Idx < num4Idx && nums[num4Idx] == nums[num4Idx + 1]) {
                            num4Idx--;
                        }
                        while (num3Idx < num4Idx && nums[num3Idx] == nums[num3Idx - 1]) {
                            num3Idx++;
                        }
                    } else if (sum > target) {
                        num4Idx--;
                    } else {
                        num3Idx++;
                    }
                }

            }
        }

        return result;
    }
}
