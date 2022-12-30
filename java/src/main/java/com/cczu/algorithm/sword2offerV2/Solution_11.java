package com.cczu.algorithm.sword2offerV2;

/**
 * 剑指 Offer 14- I. 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 示例 1：
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 */
public class Solution_11 {
    public static void main(String[] args) {
        Solution_11 solution_11 = new Solution_11();
        System.out.println(solution_11.cuttingRope(2));
    }

    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        int result = 1;
        // 分隔 3段是最长的
        while (n > 4) {
            n -= 3;
            result = result * 3;
        }
        return result * n;
    }
}
