package com.cczu.algorithm.sword2offerV2;

/**
 * 剑指 Offer 16. 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 * 示例 2：
 * <p>
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 * 示例 3：
 * <p>
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2-2 = 1/22 = 1/4 = 0.25
 */
public class Solution_14 {
    public static void main(String[] args) {
        Solution_14 solution_14 = new Solution_14();
        System.out.println(solution_14.myPow(2, 4));
    }

    public double myPow(double x, int n) {
        if (x == 0) {
            return 0;
        }
        // -n 可能越界，所以用 long来存储
        long b = n;
        if (b < 0) {
            // 为负数时，取倒数，n取反
            x = 1 / x;
            b = -b;
        }
        double res = 1L;
        while (b > 0) {
            // 当b为奇数时， x^n = x*(x^2)^n/2
            // 当b为偶数时， x^n = (x^2)^n/2
            if ((b & 1) == 1) {
                res = res * x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
