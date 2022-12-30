package com.cczu.algorithm.leetcode;

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 * <p>
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 * <p>
 * Return the maximum amount of water a container can store.
 * <p>
 * Notice that you may not slant the container.
 *
 * @author yjz
 * @date 2022/1/27
 */
public class Solution_11 {
    public static void main(String[] args) {
        int[] tmp = {1,8,6,2,5,4,8,3,7};
        System.out.println(new Solution_11().maxArea(tmp));
    }

    public int maxArea(int[] height) {
        int maxArea = 0;
        int lo = 0;
        int hi = height.length - 1;
        while (lo < hi) {
            int area = Math.min(height[lo], height[hi]) * (hi - lo);
            maxArea = Math.max(maxArea, area);
            if (height[lo] > height[hi]) {
                hi--;
            } else {
                lo++;
            }
        }
        return maxArea;
    }
}
