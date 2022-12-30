package com.cczu.algorithm.leetcode;

/**
 * 96. Unique Binary Search Trees
 * Medium
 * <p>
 * 7426
 * <p>
 * 298
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: 5
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: 1
 */
public class Solution_96 {
    public static void main(String[] args) {
        Solution_96 solution_96 = new Solution_96();
        System.out.println(solution_96.numTrees(3));
    }

    public int numTrees(int n) {
        /*
         *1. 假设两个变量  G(n) 表示 a number of unique trees for a sequence of length n
         *  F(i,n) 1 <= i <= n;表示 the number of unique trees , where the number of i is the root of bst
         *2. 得出结论是 G(n) = F(1,n) + F(2,n) + F(3,n) + F(4,n) + F(5,n) + F(n,n)
         *3. F(2,n) 等于左子树的数量*右子树的数量。 (1) 和（3,4,5,6,n)的数量
         *  F(2,n) = G(2 - 1)*G(n-2)
         * 特殊情况：G(0) = G(1) = 1
         *4. 推出 G(n) = G(0)*G(n -1) + G(1) * G(n -2) + .... + G(n -1) * G(0)
         */

        int[] res = new int[n + 1];
        res[0] = res[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 遍历 G(n) = F(1,n) + F(2,n) + F(3,n) + F(4,n) + F(5,n) + F(n,n)
            for (int j = 1; j <= i; j++) {
                // 遍历 G(n) = G(0)*G(n -1) + G(1) * G(n -2) + .... + G(n -1) * G(0)
                res[i] += res[j - 1] * res[i - j];
            }
        }
        return res[n];
    }
}
