package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * Medium
 * <p>
 * 4042
 * <p>
 * 138
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two integers n and k, return all possible combinations of k numbers out of the range [1, n].
 * <p>
 * You may return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * Example 2:
 * <p>
 * Input: n = 1, k = 1
 * Output: [[1]]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 20
 * 1 <= k <= n
 *
 * @author yjz
 * @date 2022/4/20
 */
public class Solution_77 {
    public static void main(String[] args) {
        Solution_77 solution_77 = new Solution_77();
        System.out.println(solution_77.combine(4, 2));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    /**
     * 回溯计算
     */
    public void backTrack(List<List<Integer>> result, List<Integer> tmpResult, int n, int k, int startIndex) {
        if (tmpResult.size() == k) {
            result.add(new ArrayList<>(tmpResult));
        } else {
            for (int i = startIndex; i <= n; i++) {
                tmpResult.add(i);
                backTrack(result, tmpResult, n, k, i + 1);
                tmpResult.remove(tmpResult.size() - 1);
            }
        }
    }
}
