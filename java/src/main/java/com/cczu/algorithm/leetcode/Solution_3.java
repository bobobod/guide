package com.cczu.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Solution_3 {
    public static void main(String[] args) {
        Solution_3 solution_3 = new Solution_3();
        System.out.println(solution_3.lengthOfLongestSubstring("pwwkew"));
    }

    public int lengthOfLongestSubstring(String s) {
        // 思路：使用hashMap记录每个元素的key和索引位置，同时使用双指针统计最长的长度
        if (s == null || s.length() == 0) {
            return 0;
        }
        // max 用于记录最大的长度
        int max = 0;
        // j 用于记录最开始不相同元素的起始端
        int j = 0;
        Map<Character, Integer> characterMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (characterMap.containsKey(c)) {
                // 目前map中存在这个key，将索引位置+1 即为第一个不同元素的问题
                j = Math.max(j, characterMap.get(c) + 1);
            }
            characterMap.put(c, i);
            max = Math.max(max, i - j + 1);
        }
        return max;
    }
}
