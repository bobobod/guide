package com.cczu.algorithm.leetcode;

/**
 * 79. Word Search
 * Add to List
 * <p>
 * Share
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'SEE'
 * Output: true
 * Example 3:
 * <p>
 * <p>
 * Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCB'
 * Output: false
 *
 * @author yjz
 * @date 2022/4/24
 */
public class Solution_79 {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (checkExist(board, i, j, words, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkExist(char[][] board, int i, int j, char[] words, int wordIndex) {
        // 如果达到word最后一个长度的话，表示匹配成功
        if (wordIndex == words.length) {
            return true;
        }
        // 边界值处理
        if (i < 0 || j < 0 || i == board.length || j == board[i].length) {
            return false;
        }
        // 不等的时候直接返回
        if (words[wordIndex] != board[i][j]) {
            return false;
        }


        boolean flag = false;
        // 标记已经被标记了,ascill 表最大是255
        board[i][j] ^= 256;
        flag = checkExist(board, i + 1, j, words, wordIndex + 1) || checkExist(board, i, j + 1, words, wordIndex + 1)
                || checkExist(board, i - 1, j, words, wordIndex + 1) || checkExist(board, i, j - 1, words, wordIndex + 1);
        // 取消上一点的标记
        board[i][j] ^= 256;
        return flag;

    }

    public static void main(String[] args) {
        Solution_79 solution_79 = new Solution_79();
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        System.out.println(solution_79.exist(board, "SEE"));
    }
}
