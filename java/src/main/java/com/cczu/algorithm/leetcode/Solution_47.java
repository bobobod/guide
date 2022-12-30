package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * Example 2:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * @author yjz
 * @date 2022/3/1
 */
public class Solution_47 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        Solution_47 solution_47 = new Solution_47();
        System.out.println(solution_47.permuteUnique(nums));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        // 回溯
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, ArrayList<Integer> tmpList, int[] nums, boolean[] flags) {
        if (tmpList.size() == nums.length) {
            // 新建一个数组
            result.add(new ArrayList<>(tmpList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (flags[i] || (i > 0 && nums[i] == nums[i - 1] && flags[i - 1])) {
                    continue;
                }
                int num = nums[i];
                flags[i] = true;
                tmpList.add(num);
                backtrack(result, tmpList, nums, flags);
                tmpList.remove(tmpList.size() - 1);
                flags[i] = false;
            }
        }
    }
}
