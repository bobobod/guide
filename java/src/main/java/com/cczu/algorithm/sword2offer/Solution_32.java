package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 032. 有效的变位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断它们是不是一组变位词（字母异位词）。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同且字符顺序不完全相同，则称 s 和 t 互为变位词（字母异位词）。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "rat", t = "car"
 * 输出: false
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "a"
 * 输出: false
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, t.length <= 5 * 104
 * s and t 仅包含小写字母
 */
public class Solution_32 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length() || s.equals(t)) {
            // 特殊情况
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 相互抵消
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution_32 solution_32 = new Solution_32();
        System.out.println(solution_32.isAnagram("anagram", "nagaram"));
    }
}
