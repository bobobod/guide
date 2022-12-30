package com.cczu.algorithm.leetcode;

/**
 * 69. Sqrt(x)
 * Easy
 * <p>
 * 3606
 * <p>
 * 3103
 * <p>
 * Add to List
 * <p>
 * Share
 * Given a non-negative integer x, compute and return the square root of x.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
 * <p>
 * Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: x = 4
 * Output: 2
 * Example 2:
 * <p>
 * Input: x = 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.
 *
 * @author yjz
 * @date 2022/4/7
 */
public class Solution_69 {
    public static void main(String[] args) {
        Solution_69 solution_69 = new Solution_69();
        System.out.println(solution_69.mySqrt(2147483647));
        System.out.println(solution_69.mySqrt(9));
        System.out.println(solution_69.mySqrt(7));
    }

    public int mySqrt(int x) {
        // 限制 r * r <= x  && (r + 1) * (r + 1) > x
        if (x == 0) {
            return 0;
        }
        int start = 1, end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // a little trick use x/mid avoid integer limit
            if ((mid <= x / mid) && ((mid + 1) > x / (mid + 1))) {
                return mid;
            } else if (mid > x / mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }
}
