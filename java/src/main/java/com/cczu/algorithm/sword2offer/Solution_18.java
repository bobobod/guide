package com.cczu.algorithm.sword2offer;

/**
 * 题目描述
 * 评论 (184)
 * 题解 (269)
 * 提交记录
 * 剑指 Offer II 018. 有效的回文
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 本题中，将空字符串定义为有效的 回文串 。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "A man, a plan, a canal: Panama"
 * 输出: true
 * 解释："amanaplanacanalpanama" 是回文串
 * 示例 2:
 * <p>
 * 输入: s = "race a car"
 * 输出: false
 * 解释："raceacar" 不是回文串
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 2 * 105
 * 字符串 s 由 ASCII 字符组成
 */
public class Solution_18 {
    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    public static void main(String[] args) {
        Solution_18 solution_18 = new Solution_18();
        System.out.println(solution_18.isPalindrome("A man, a plan, a canal: Panama"));
    }
}
