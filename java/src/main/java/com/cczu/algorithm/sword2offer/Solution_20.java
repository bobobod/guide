package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 020. 回文子字符串的个数
 * 给定一个字符串 s ，请计算这个字符串中有多少个回文子字符串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入：s = "aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 */
public class Solution_20 {
    public int countSubstrings(String s) {
        /*
         * 解法：中心拓展
         * 枚举n=3的字符串，能生成多少个回文串？
         * 中心节点第一个左边节点   右边节点
         * 1. 0  0
         * 2. 0  1
         * 3. 1  1
         * 4. 1  2
         * 5. 2  2
         * 6. 2  3
         * 7. 3  3
         *
         * 结论：n = 3, 可以得到 2*n - 1个回文串，左边的节点：i/2, 右边的节点： i/2 + (i mod 2)
         * 前置知识：一个回文串的子串也是回文串
         */
        int len = s.length(), result = 0;
        for (int i = 0; i < 2 * len - 1; i++) {
            int left = i / 2, right = i / 2 + (i % 2);
            while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Solution_20().countSubstrings("aaa"));
    }
}
