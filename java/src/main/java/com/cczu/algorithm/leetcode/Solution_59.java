package com.cczu.algorithm.leetcode;

/**
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 *
 * @author yjz
 * @date 2022/3/28
 */
public class Solution_59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int tmp = 1;
        int rowBegin = 0;
        int colBegin = 0;
        int rowEnd = n - 1;
        int colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // traverse right
            for (int i = colBegin; i <= colEnd; i++) {
                res[rowBegin][i] = tmp++;
            }
            rowBegin++;
            // traverse down
            for (int i = rowBegin; i <= rowEnd; i++) {
                res[i][colEnd] = tmp++;
            }
            colEnd--;
            // traverse left
            if (rowBegin < rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    res[rowEnd][i] = tmp++;
                }
                rowEnd--;
            }
            // traverse up
            if (colBegin < colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res[i][colBegin] = tmp++;
                }
                colBegin++;
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
