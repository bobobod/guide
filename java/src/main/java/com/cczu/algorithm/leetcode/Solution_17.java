package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 * <p>
 * Input: digits = ""
 * Output: []
 * Example 3:
 * <p>
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * @author yjz
 * @date 2022/2/14
 */
public class Solution_17 {
    public static void main(String[] args) {
        Solution_17 solution_17 = new Solution_17();
        System.out.println(solution_17.letterCombinations(""));
    }

    public List<String> letterCombinations(String digits) {
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        if (digits.length() != 0){
            helper(digits, "", map, result);
        }
        return result;
    }

    private void helper(String str, String op, String[] map, List<String> result) {
        if (str == null || str.length() == 0) {
            result.add(op);
            return;
        }
        // 获取当前第一个字符
        int i = str.charAt(0) - '0';
        for (int j = 0; j < map[i].length(); j++) {
            helper(str.substring(1), op + map[i].charAt(j), map, result);
        }

    }
}
