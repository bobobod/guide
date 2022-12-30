package com.cczu.algorithm.leetcode;

/**
 * 5. Longest Palindromic Substring
 * Medium
 * <p>
 * 21741
 * <p>
 * 1249
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a string s, return the longest palindromic substring in s.
 * <p>
 * A string is called a palindrome string if the reverse of that string is the same as the original string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 * <p>
 * Input: s = "cbbd"
 * Output: "bb"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 * Accepted
 * 2,116,820
 * Submissions
 * 6,537,414
 */
public class Solution_05 {
    public String longestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }
        int maxLen = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len = 1;
            int left = i - 1;
            int right = i + 1;
            while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                left--;
                len++;
            }
            while (right < s.length() && s.charAt(right) == s.charAt(i)) {
                right++;
                len++;
            }
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                len = len + 2;
            }
            if (len > maxLen) {
                start = left;
                maxLen = len;
            }
        }
        return s.substring(start + 1, start + maxLen + 1);
    }

    public static void main(String[] args) {
        Solution_05 solution_05 = new Solution_05();
        System.out.println(solution_05.longestPalindrome("babad"));
    }
}
