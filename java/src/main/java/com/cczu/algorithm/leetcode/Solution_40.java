package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
 * <p>
 * Each number in candidates may only be used once in the combination.
 * <p>
 * Note: The solution set must not contain duplicate combinations.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,5,2,1,2], target = 5
 * Output:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * @author yjz
 * @date 2022/2/22
 */
public class Solution_40 {
    public static void main(String[] args) {
        Solution_40 solution_39 = new Solution_40();
        int[] candidates = {2, 3, 6, 7};
        System.out.println(solution_39.combinationSum2(candidates, 7));
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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
                // 元素不可以重复，从i+1开始
                backtracking(candidates, result, tmpList, remain - candidates[i], i + 1);
                // 删除最后一个新增的元素
                tmpList.remove(tmpList.size() - 1);

            }
        }

    }
}
