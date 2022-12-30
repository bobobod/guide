package com.cczu.algorithm.leetcode;

/**
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 * <p>
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 * <p>
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 * <p>
 * The replacement must be in place and use only constant extra memory.
 *
 * @author yjz
 * @date 2022/2/17
 */
public class Solution_31 {
    public static void main(String[] args) {
        Solution_31 solution_31 = new Solution_31();
        int[] nums = {5,1,1};
        solution_31.nextPermutation(nums);
        for (int num : nums) {
            System.out.println(num);
        }
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从右往前找 前一个比后一个小的数据
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            // 往后找到第一个比nums[i]大的数进行交换
            while (nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        // 反转列表 从i+1开始
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public void reverse(int[] nums, int reversedIndex) {
        int tail = nums.length - 1;
        while (reversedIndex < tail) {
            swap(nums, reversedIndex++, tail--);
        }
    }
}
