package com.cczu.algorithm.sword2offer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 039. 直方图最大矩形面积
 * 给定非负整数数组 heights ，数组中的数字用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 */
public class Solution_39 {
    public int largestRectangleArea(int[] heights) {
        /*
         * 单调栈实现，维护一个递增的序列
         * 核心：找到每根柱子左右最宽的距离
         * 遍历结束后如果仍然有柱子的话，说明右边没有比它矮的柱子了，可以想象成以当前柱子为顶的最大矩阵的下标往右延伸到下标的数组长度的乘积
         */
        Deque<Integer> deque = new LinkedList<>();
        // 左边push一个-1，防止左边界为空
        deque.push(-1);
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            while (deque.peek() != -1 && heights[i] < heights[deque.peek()]) {
                int height = heights[deque.pop()];
                int width = i - deque.peek() - 1;
                // 计算面积
                int area = height * width;
                maxArea = Math.max(maxArea, area);
            }
            deque.push(i);
        }
        // 剩下的依次弹出
        while (deque.peek() != -1) {
            int height = heights[deque.pop()];
            int width = heights.length - deque.peek() - 1;
            maxArea = Math.max(height * width, maxArea);
        }
        return maxArea;

    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        Solution_39 solution_39 = new Solution_39();
        System.out.println(solution_39.largestRectangleArea(arr));
    }
}
