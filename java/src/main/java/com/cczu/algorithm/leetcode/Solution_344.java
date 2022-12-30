package com.cczu.algorithm.leetcode;

/**
 * Write a function that reverses a string. The input string is given as an array of characters char[].
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 *
 * You may assume all the characters consist of printable ascii characters.
 *
 *
 *
 * Example 1:
 *
 * Input: ["h","e","l","l","o"]
 * Output: ["o","l","l","e","h"]
 * Example 2:
 *
 * Input: ["H","a","n","n","a","h"]
 * Output: ["h","a","n","n","a","H"]
 *
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_344 {
    public void reverseString(char[] s) {
        int start = 0,end = s.length - 1;
        for (;start < end; start++,end--) {
            char tmp = s[start];
            s[start] = s[end];
            s[end] = tmp;
        }
    }
}
