package com.cczu.algorithm.leetcode;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * Example:
 *
 * Input:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * Output: 7
 * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
 *
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0){
                    dp[0][0] = grid[0][0];
                    continue;
                }
                if (i == 0){
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                }
                else if (j == 0){
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.min(dp[i][j - 1],dp[i - 1][j]) + grid[i][j];
                }
            }
        }
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
//        int[][] a = {{1,2,3},{4,5,4,6}};
//        System.out.println(a.length);
//        System.out.println(a[1].length);
    }
}
