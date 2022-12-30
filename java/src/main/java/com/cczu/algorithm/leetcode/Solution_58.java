package com.cczu.algorithm.leetcode;

/**
 * Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.
 * <p>
 * A word is a maximal substring consisting of non-space characters only.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "Hello World"
 * Output: 5
 * Explanation: The last word is "World" with length 5.
 * Example 2:
 * <p>
 * Input: s = "   fly me   to   the moon  "
 * Output: 4
 * Explanation: The last word is "moon" with length 4.
 * Example 3:
 * <p>
 * Input: s = "luffy is still joyboy"
 * Output: 6
 * Explanation: The last word is "joyboy" with length 6.
 *
 * @author yjz
 * @date 2022/3/27
 */
public class Solution_58 {
    public int lengthOfLastWord(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int length = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                length++;
            } else {
                if (length > 0) {
                    return length;
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        Solution_58 solution_58 = new Solution_58();
        System.out.println(solution_58.lengthOfLastWord("hello world"));
    }
}
