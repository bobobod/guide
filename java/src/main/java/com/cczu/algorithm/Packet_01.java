package com.cczu.algorithm;

/**
 * 0-1 背包问题
 */
public class Packet_01 {
    public static void main(String[] args) {
        // 背包的重量
        int[] weightArr = {1, 3, 4};
        // 背包的价值
        int[] valArr = {15, 20, 30};
        Packet_01 packet_01 = new Packet_01();
        System.out.println(packet_01.knapsackPacket(weightArr, valArr, 4));
    }

    /**
     * @param weightArr 重量
     * @param valArr    价值
     * @param size      背包大小
     * @return 最大价值
     */
    public int knapsackPacket(int[] weightArr, int[] valArr, int size) {

        // 定义一个dp数组 dp[i][j] 表示背包容量是j，前i个背包最大的价值
        int[][] dp = new int[weightArr.length + 1][size + 1];
        // 当背包容量为0时，赋初始值0
        for (int i = 0; i <= weightArr.length; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= weightArr.length; i++) {
            for (int j = 1; j <= size; j++) {
                if (weightArr[i - 1] <= j) {
                    // 取最大值，要么是前一个物品的最大值 or 取前一个物品以及容量为减去当前占用容量的值 + 当前物品的价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weightArr[i - 1]] + valArr[i - 1]);
                } else {
                    // 直接取 前i-1个物品的最大价值
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[weightArr.length][size];
    }
}
