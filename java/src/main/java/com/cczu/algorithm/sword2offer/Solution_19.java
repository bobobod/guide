package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 019. 最多删除一个字符得到回文
 * 给定一个非空字符串 s，请判断如果 最多 从字符串中删除一个字符能否得到一个回文字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "abca"
 * 输出: true
 * 解释: 可以删除 "c" 字符 或者 "b" 字符
 * 示例 3:
 * <p>
 * 输入: s = "abc"
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 */
public class Solution_19 {
    public boolean validPalindrome(String s) {
        return helper(s, 0, s.length() - 1, true);
    }

    private boolean helper(String s, int start, int end, boolean flag) {

        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                // flag用来标记是否已经去掉一个数字
                if (!flag) {
                    return false;
                }
                return helper(s, start + 1, end, false) || helper(s, start, end - 1, false);
            }
            start++;
            end--;
        }
        return true;

    }

    public static void main(String[] args) {
        Solution_19 solution_19 = new Solution_19();
//        System.out.println(solution_19.validPalindrome("eeccccbebaeeabebccceea"));
//        System.out.println(solution_19.validPalindrome("aba"));
        System.out.println(solution_19.validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }
}
