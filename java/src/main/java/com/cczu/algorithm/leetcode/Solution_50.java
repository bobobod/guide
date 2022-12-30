package com.cczu.algorithm.leetcode;

/**
 * Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 2.00000, n = 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: x = 2.10000, n = 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: x = 2.00000, n = -2
 * Output: 0.25000
 * Explanation: 2-2 = 1/22 = 1/4 = 0.25
 *
 * @author yjz
 * @date 2022/3/16
 */
public class Solution_50 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            // 避免integer溢出
            return 1 / x * myPow(1 / x, -(n + 1));
        }
        return n % 2 == 0 ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }

    public static void main(String[] args) {
        Solution_50 solution_50 = new Solution_50();
        System.out.println(solution_50.myPow(2, 4));
        System.out.println(solution_50.myPow(4, -2));

    }
}
