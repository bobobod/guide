package com.cczu.algorithm.sword2offer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 015. 字符串中的所有变位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 变位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 变位词 指字母相同，但排列不同的字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的变位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的变位词。
 * 示例 2：
 * <p>
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的变位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的变位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的变位词。
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class Solution_15 {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return Collections.emptyList();
        }
        List<Integer> result = new ArrayList<>();
        int[] cnt = new int[26];
        for (int i = 0; i < p.length(); i++) {
            --cnt[p.charAt(i) - 'a'];
        }
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'a';
            ++cnt[index];
            while (cnt[index] > 0) {
                --cnt[s.charAt(left) - 'a'];
                left++;
            }
            if (right - left + 1 == p.length()) {
                result.add(left);
                --cnt[s.charAt(left) - 'a'];
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_15 solution_15 = new Solution_15();
        System.out.println(solution_15.findAnagrams("abab", "ab"));
    }
}
