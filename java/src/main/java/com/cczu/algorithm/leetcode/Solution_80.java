package com.cczu.algorithm.leetcode;

/**
 * Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * <p>
 * Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.
 * <p>
 * Return k after placing the final result in the first k slots of nums.
 * <p>
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Custom Judge:
 * <p>
 * The judge will test your solution with the following code:
 * <p>
 * int[] nums = [...]; // Input array
 * int[] expectedNums = [...]; // The expected answer with correct length
 * <p>
 * int k = removeDuplicates(nums); // Calls your implementation
 * <p>
 * assert k == expectedNums.length;
 * for (int i = 0; i < k; i++) {
 * assert nums[i] == expectedNums[i];
 * }
 * If all assertions pass, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * Example 2:
 * <p>
 * Input: nums = [0,0,1,1,1,1,2,3,3]
 * Output: 7, nums = [0,0,1,1,2,3,3,_,_]
 * Explanation: Your function should return k = 7, with the first seven elements of nums being 0, 0, 1, 1, 2, 3 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 *
 * @author yjz
 * @date 2022/5/25
 */
public class Solution_80 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        Solution_80 solution_80 = new Solution_80();
        int i = solution_80.removeDuplicates(nums);
        System.out.println(i);
        for (int j = 0; j < i; j++) {
            System.out.println(nums[j]);
        }
    }

    //    public int removeDuplicates(int[] nums) {
//        int len = nums.length;
//        if (len == 0) {
//            return 0;
//        }
//        if (len == 1) {
//            return 1;
//        }
//        int currElement = nums[0];
//        int cnt = 1;
//        for (int i = 1; i < len; i++) {
//            int num = nums[i];
//            if (num == currElement) {
//                ++cnt;
//                if (cnt > 2) {
//                    for (int j = i; j < len - 1; j++) {
//                        nums[j] = nums[j + 1];
//                    }
//                    i--;
//                    len--;
//                }
//            } else {
//                currElement = num;
//                cnt = 1;
//            }
//        }
//        return len;
//    }
    // excellent
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int num : nums) {
            // 条件一：如果长度小于2  条件二：当前值大于前2位的数值则进行赋值
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num;
            }
        }
        return i;
    }


}
