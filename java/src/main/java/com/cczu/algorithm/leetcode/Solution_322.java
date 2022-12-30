package com.cczu.algorithm.leetcode;

/**
 * 322. Coin Change
 * Medium
 * <p>
 * 13967
 * <p>
 * 314
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * <p>
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * <p>
 * You may assume that you have an infinite number of each kind of coin.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 * <p>
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 * <p>
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 * Accepted
 * 1,194,394
 * Submissions
 */
public class Solution_322 {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        return helper(coins, amount, new int[amount]);

    }

    private int helper(int[] coins, int remain, int[] records) {
        if (remain < 0) {
            return -1; // not valid
        }
        if (remain == 0) {
            return 0; // completed
        }
        if (records[remain - 1] != 0) {
            return records[remain - 1]; // duplicate computing
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int res = helper(coins, remain - coins[i], records);
            if (res >= 0 && res < min) {
                // compute minimum
                min = 1 + res;
            }
        }

        records[remain - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return records[remain - 1];
    }

    public static void main(String[] args) {
        Solution_322 solution_322 = new Solution_322();
        int[] coins = {2, 5, 10 ,1};
        System.out.println(solution_322.coinChange(coins, 27));
    }

}
