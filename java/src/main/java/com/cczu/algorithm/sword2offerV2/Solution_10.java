package com.cczu.algorithm.sword2offerV2;

/**
 * 剑指 Offer 12. 矩阵中的路径
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * <p>
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * <p>
 * <p>
 * <p>
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 */
public class Solution_10 {
    public static void main(String[] args) {
        Solution_10 solution_10 = new Solution_10();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(solution_10.exist(board, "ABCCED"));

    }

    public boolean exist(char[][] board, String word) {
        if (board.length == 0) {
            return false;
        }
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (helper(board, i, j, words, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(char[][] board, int i, int j, char[] words, int cur) {
        if (cur == words.length) {
            return true;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || cur >= words.length) {
            return false;
        }
        if (board[i][j] != words[cur]) {
            return false;
        }

        char tmp = board[i][j];
        boolean flag;
        board[i][j] = '\0';
        flag = helper(board, i - 1, j, words, cur + 1) || helper(board, i + 1, j, words, cur + 1) || helper(board, i, j - 1, words, cur + 1) || helper(board, i, j + 1, words, cur + 1);
        board[i][j] = tmp;
        return flag;

    }
}
