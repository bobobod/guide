package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 014. 字符串中的变位词
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s1.length, s2.length <= 104
 * s1 和 s2 仅包含小写字母
 */
public class Solution_14 {
    public boolean checkInclusion(String s1, String s2) {
        /*
         * 思路很骚气
         * 1. 前一个子串，赋值都为-1
         * 2. 采用双指针，保证一段范围内的的数组值都为0
         */
        if (s1.length() > s2.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            arr[s1.charAt(i) - 'a']--;
        }
        int left = 0;
        for (int right = 0; right < s2.length(); right++) {
            int index = s2.charAt(right) - 'a';
            ++arr[index];
            while (arr[index] > 0) {
                // 移动左指针
                --arr[s2.charAt(left) - 'a'];
                left++;
            }
            if (right - left + 1 == s1.length()) {
                // 符合范围条件
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_14 solution_14 = new Solution_14();
        System.out.println(solution_14.checkInclusion("ab", "eidbaooo"));
    }

}
