package com.cczu.algorithm.leetcode;

import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
 * <p>
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * <p>
 * Note:
 * <p>
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 *
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_120 {

    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int size = triangle.size();
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < size; i++) {
            List<Integer> list = triangle.get(i);
            dp[i][0] = list.get(0) + dp[i - 1][0];
            for (int j = 1; j < list.size(); j++) {
                if (j == list.size() - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + list.get(j);
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + list.get(j);
                }
            }
        }
        int minVal = dp[size - 1][0];
        for (int i = 1; i < size; i++) {
            minVal = Math.min(dp[size - 1][i], minVal);
        }
        return minVal;
    }
}
