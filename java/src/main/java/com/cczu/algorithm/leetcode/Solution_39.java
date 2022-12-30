package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
 * <p>
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
 * <p>
 * It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8
 * Output: [[2,2,2,2],[2,3,3],[3,5]]
 * Example 3:
 * <p>
 * Input: candidates = [2], target = 1
 * Output: []
 *
 * @author yjz
 * @date 2022/2/22
 */
public class Solution_39 {
    public static void main(String[] args) {
        Solution_39 solution_39 = new Solution_39();
        int[] candidates = {2, 3, 6, 7};
        System.out.println(solution_39.combinationSum(candidates, 7));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(candidates, result, new ArrayList<>(), target, 0);
        return result;
    }


    /**
     * 回溯算法实现
     *
     * @param candidates 候选者
     * @param result     result
     * @param tmpList    tmpList
     * @param remain     保留
     * @param start      奇石
     */
    private void backtracking(int[] candidates, List<List<Integer>> result, ArrayList<Integer> tmpList, int remain, int start) {
        if (remain < 0) {
            return;
        }
        if (remain == 0) {
            // 存入新的list中
            result.add(new ArrayList<>(tmpList));
        } else {
            for (int i = start; i < candidates.length; i++) {
                // 去重复
                if (i > start && candidates[i - 1] == candidates[i]) {
                    continue;
                }
                tmpList.add(candidates[i]);
                // 元素可以重复使用
                backtracking(candidates, result, tmpList, remain - candidates[i], i);
                // 删除最后一个新增的元素
                tmpList.remove(tmpList.size() - 1);

            }
        }

    }
}
