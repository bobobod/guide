package com.cczu.algorithm.leetcode;

/**
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * <p>
 * If target is not found in the array, return [-1, -1].
 * <p>
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * @author yjz
 * @date 2022/2/19
 */
public class Solution_34 {


    public static void main(String[] args) {
        Solution_34 solution_34 = new Solution_34();
        int[] nums = {5, 7, 7, 8, 8, 10};
        int[] nums2 = {5, 5};
        int[] nums3 = {5, 7, 7, 8, 8, 10};
        for (int i : solution_34.searchRange(nums2, 5)) {
            System.out.println(i);
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        // 找到第一个比target相等的元素
        int start = binarySearch(nums, target - 0.5);
        // 找到第一个比target大1的元素
        int end = binarySearch(nums, target + 0.5);
        // 如果等于0表示没有找到元素
        if (end - start == 0) {
            return result;
        }
        result[0] = start;
        result[1] = end - 1;
        return result;

    }

    private int binarySearch(int[] nums, double target) {
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else if (nums[mid] < target) {
                start = mid + 1;
            }
        }
        return start;
    }


}
