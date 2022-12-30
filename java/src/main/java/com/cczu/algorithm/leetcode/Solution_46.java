package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 *
 * @author yjz
 * @date 2022/2/28
 */
public class Solution_46 {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        Solution_46 solution_46 = new Solution_46();
        System.out.println(solution_46.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums);
        return result;
    }

    private void backtrack(List<List<Integer>> result, ArrayList<Integer> tmpList, int[] nums) {
        if (tmpList.size() == nums.length) {
            result.add(new ArrayList<>(tmpList));
        } else {
            for (int num : nums) {
                if (tmpList.contains(num)) {
                    continue;
                }
                tmpList.add(num);
                backtrack(result, tmpList, nums);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }
}
