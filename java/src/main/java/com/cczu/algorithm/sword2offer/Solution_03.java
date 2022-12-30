package com.cczu.algorithm.sword2offer;

import java.util.Arrays;

/**
 * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
 * 给定一个非负整数 n ，请计算 0 到 n 之间的每个数字的二进制表示中 1 的个数，并输出一个数组。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 2
 * 输出: [0,1,1]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 示例 2:
 * <p>
 * 输入: n = 5
 * 输出: [0,1,1,2,1,2]
 * 解释:
 * 0 --> 0
 * 1 --> 1
 * 2 --> 10
 * 3 --> 11
 * 4 --> 100
 * 5 --> 101
 */
public class Solution_03 {
    public int[] countBits(int n) {
        /*
         * 取有效最低位, n >> 1
         * 整数的话：result[n] = result[n/2]
         * 奇数的话：result[n] = result[n/2] + 1
         */
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_03 solution_03 = new Solution_03();
        System.out.println(Arrays.toString(solution_03.countBits(5)));
    }
}
