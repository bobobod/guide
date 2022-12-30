package com.cczu.algorithm.leetcode;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 *
 * @author yjz
 * @date 2022/2/24
 */
public class Solution_43 {
    public static void main(String[] args) {
        Solution_43 solution_43 = new Solution_43();
        System.out.println(solution_43.multiply("123", "456"));
    }

    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] ans = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p = i + j;
                int q = i + j + 1;
                int total = sum + ans[q];
                ans[p] += total / 10;
                ans[q] = total % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int an : ans) {
            // 剔除首位为0的情况
            if (!(sb.length() == 0 && an == 0)) {
                sb.append(an);
            }
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
