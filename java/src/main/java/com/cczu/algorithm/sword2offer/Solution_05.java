package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 005. 单词长度的最大乘积
 * 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: words = ["abcw","baz","foo","bar","fxyz","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "fxyz"。它们不包含相同字符，且长度的乘积最大。
 * 示例 2:
 * <p>
 * 输入: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 * <p>
 * 输入: words = ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] 仅包含小写字母
 */
public class Solution_05 {
    public int maxProduct(String[] words) {
        /*
         * 思路：题目中提示所有字母都是小写字母，可以用int数组中int的bit位来记录每个字符串中占领的数字
         */
        int[] masks = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                int c = words[i].charAt(j) - 'a';
                masks[i] |= 1 << c;
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    // 说明没有重复
                    maxProduct = Math.max(words[i].length() * words[j].length(), maxProduct);
                }

            }
        }
        return maxProduct;
    }

    public static void main(String[] args) {
        Solution_05 solution_06 = new Solution_05();
        String[] strs = {"abcw","baz","foo","bar","fxyz","abcdef"};
        System.out.println(solution_06.maxProduct(strs));
    }
}
