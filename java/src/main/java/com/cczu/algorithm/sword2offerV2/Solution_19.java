package com.cczu.algorithm.sword2offerV2;

import java.util.Arrays;

/**
 * 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 */
public class Solution_19 {
    public static void main(String[] args) {
        Solution_19 solution_19 = new Solution_19();
        int[] arr = {11, 9, 3, 7, 16, 4, 2, 0};
        System.out.println(Arrays.toString(solution_19.exchange(arr)));
    }

    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (nums[left] % 2 == 1) {
                left++;
            }
            while (nums[right] % 2 == 0) {
                right--;
            }
            if (nums[left] % 2 == 0 && nums[right] % 2 == 1 && left < right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
