package com.cczu.algorithm.sword2offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 016. 不含重复字符的最长子字符串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长连续子字符串 的长度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子字符串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子字符串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 * <p>
 * 输入: s = ""
 * 输出: 0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class Solution_16 {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int result = 0;
        Map<Character, Integer> map = new HashMap<>();
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                start = Math.max(start, map.get(s.charAt(i)));
            }
            map.put(s.charAt(i), i);
            result = Math.max(result, i - start);
        }

        return result;
    }

    public static void main(String[] args) {
        Solution_16 solution_16 = new Solution_16();
        System.out.println(solution_16.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(solution_16.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution_16.lengthOfLongestSubstring("pwwkew"));
    }
}
