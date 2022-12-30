package com.cczu.algorithm.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * 239. Sliding Window Maximum
 * Hard
 * <p>
 * 12036
 * <p>
 * 385
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the
 * array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class Solution_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        int index = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            /*
             * 使用arrayDeque的特性
             * 1. 保证首部是每个滑动窗口的最大值，且首部的索引符合k范围内的
             * 2. 保证除首部外第二个元素是前进序列的第二大值的序列索引号
             */
            while (!deque.isEmpty() && (i - deque.peek() >= k)) {
                deque.pop();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(i);
            if (i - k + 1 >= 0) {
                result[index++] = nums[deque.peek()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution_239 solution_239 = new Solution_239();
        int[] nums = {1, 3, 1, 2, 0, 5};
        System.out.println(Arrays.toString(solution_239.maxSlidingWindow(nums, 3)));
    }
}
