package com.cczu.algorithm.leetcode;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:
 * <p>
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 * @author yjz
 * @date 2022/4/16
 */
public class Solution_74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rowSize = matrix.length - 1, colSize = matrix[0].length - 1;
        for (int i = 0; i <= rowSize; i++) {
            if (matrix[i][0] <= target && matrix[i][colSize] >= target) {
                int start = 0;
                int end = colSize;
                while (start <= end) {
                    int mid = start + (end - start) / 2;
                    if (matrix[i][mid] == target) {
                        return true;
                    } else if (matrix[i][mid] > target) {
                        end = mid - 1;
                    } else {
                        start = mid + 1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        Solution_74 solution_74 = new Solution_74();
        System.out.println(solution_74.searchMatrix(matrix, 3));
    }
}
