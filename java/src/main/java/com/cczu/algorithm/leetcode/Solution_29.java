package com.cczu.algorithm.leetcode;

/**
 * 29. Divide Two Integers
 * Medium
 * <p>
 * 2567
 * <p>
 * 9046
 * <p>
 * Add to List
 * <p>
 * Share
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * <p>
 * The integer division should truncate toward zero, which means losing its fractional part. For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231, 231 − 1]. For this problem, if the quotient is strictly greater than 231 - 1, then return 231 - 1, and if the quotient is strictly less than -231, then return -231.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Explanation: 10/3 = 3.33333.. which is truncated to 3.
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Explanation: 7/-3 = -2.33333.. which is truncated to -2.
 *
 * @author yjz
 * @date 2022/3/5
 */
public class Solution_29 {
    public static void main(String[] args) {
        Solution_29 solution_29 = new Solution_29();
        System.out.println(solution_29.divide(4, 4));
    }

    public int divide(int dividend, int divisor) {
        // 防止溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 做异或运算，获取最终的结果的是否是负数
        boolean isNegative = dividend < 0 ^ divisor < 0;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        // 商
        int quotient = 0;
        // 子商
        int subQuotient = 0;
        while (dividend - divisor >= 0) {
            // 通过指数型增长，快速获取商值   divisor << subQuotient << 1   等同于  divisor << （subQuotient + 1）
            for (subQuotient = 0; dividend - (divisor << subQuotient << 1) >= 0; subQuotient++) {
            }
            //加入结果集
            quotient += 1 << subQuotient;
            //进行下一轮迭代
            dividend -= divisor << subQuotient;

        }
        return isNegative ? -quotient : quotient;
    }

}
