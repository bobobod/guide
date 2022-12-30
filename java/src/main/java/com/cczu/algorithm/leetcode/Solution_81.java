package com.cczu.algorithm.leetcode;

/**
 * 81. Search in Rotated Sorted Array II
 * Medium
 * <p>
 * 4486
 * <p>
 * 729
 * <p>
 * Add to List
 * <p>
 * Share
 * There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
 * <p>
 * Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
 * <p>
 * Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
 * <p>
 * You must decrease the overall operation steps as much as possible.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 */
public class Solution_81 {
    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        Solution_81 solution_81 = new Solution_81();
        System.out.println(solution_81.search(nums, 3));
    }

    public boolean search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int tmp = nums[mid];
            if (tmp == target) {
                return true;
            }
            // 情况一，if we can sure right side is sorted  or left side is unsorted
            if (tmp < nums[hi] || tmp < nums[lo]) {
                if (target > tmp && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            // 情况二， if we can sure left side is sorted or right side is unsorted
            else if (tmp > nums[lo] || tmp > nums[hi]) {
                if (target >= nums[lo] && target < tmp) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            // 情况三：nums[left] == nums[mid] == nums[end],顺便移动一位即可
            else {
                hi--;
            }
        }
        return false;
    }
}
