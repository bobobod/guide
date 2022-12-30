package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 002. 二进制加法
 * 给定两个 01 字符串 a 和 b ，请计算它们的和，并以二进制字符串的形式输出。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "10"
 * 输出: "101"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * <p>
 * 注意：本题与主站 67 题相同：https://leetcode-cn.com/problems/add-binary/
 */
public class Solution_02 {
    public String addBinary(String a, String b) {
        int lenA = a.length(), lenB = b.length();
        int maxLen = Math.max(lenA, lenB);
        int subLen = Math.abs(lenA - lenB);
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = maxLen - 1; i >= 0; i--) {
            int tmpA = 0, tmpB = 0;
            if (lenA > lenB) {
                tmpA = a.charAt(i) - '0';
                if (i - subLen >= 0) {
                    tmpB = b.charAt(i - subLen) - '0';
                }
            } else {
                tmpB = b.charAt(i) - '0';
                if (i - subLen >= 0) {
                    tmpA = a.charAt(i - subLen) - '0';
                }
            }
            int sum = tmpA + tmpB + carry;
            if (sum > 1) {
                sum = sum % 2;
                carry = 1;
            } else {
                carry = 0;
            }
            sb.append(sum);
        }
        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        Solution_02 solution_02 = new Solution_02();
        System.out.println(solution_02.addBinary("01", "10"));
    }
}
