package com.cczu.algorithm.leetcode;

/**
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * Follow up:
 *
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 *
 * @author jianzhen.yin
 * @date 2020/9/25
 */
public class Solution_189 {
    public static void rotate(int[] nums, int k) {
        swap(nums,0,nums.length - 1);
        swap(nums,0,k % nums.length - 1);
        swap(nums,k % nums.length,nums.length - 1);
    }
    private static void swap(int[] nums,int start,int end){
       for (;start < end;start++,end--){
           int tmp = nums[start];
           nums[start] = nums[end];
           nums[end] = tmp;
       }
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7};
        rotate(a,2);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        System.out.println(a);
    }
}
