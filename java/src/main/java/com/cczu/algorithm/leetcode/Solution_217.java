package com.cczu.algorithm.leetcode;

import java.util.HashSet;

/**
 * 217. Contains Duplicate
 * Easy
 * <p>
 * 5749
 * <p>
 * 989
 * <p>
 * Add to List
 * <p>
 * Share
 *  Given an integer array nums, return true if any value appears at least twice in the array,
 * and return false if every element is distinct.
 * <p>
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Example 3:
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 */
public class Solution_217 {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])){
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        Solution_217 solution_217 = new Solution_217();
        int[] arr = {1,2,3,4};
        System.out.println(solution_217.containsDuplicate(arr));
    }
}
