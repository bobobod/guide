package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: ["()"]
 *
 * @author yjz
 * @date 2021/12/23
 */
public class Solution_22 {
    public static void main(String[] args) {
        Solution_22 solution_22 = new Solution_22();
        System.out.println(solution_22.generateParenthesis(3));
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        /*
            左括号 < n;
            右括号数 < 左括号数
         */
        helper(res, 0, 0, n, "");
        return res;
    }

    /**
     * 构建
     *
     * @param res   结果集
     * @param left  左括号个数
     * @param right 后括号个数
     * @param n     层数
     */
    private void helper(List<String> res, int left, int right, int n, String content) {
        // terminator
        if (left == n && right == n) {
            res.add(content);
            return;
        }
        // process this level

        // drill down
        if (left < n) {
            helper(res, left + 1, right, n, content + "(");
        }
        if (right < left) {
            helper(res, left, right + 1, n, content + ")");
        }
    }

}
