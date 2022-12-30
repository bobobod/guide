package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * Example 2:
 * <p>
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * @author yjz
 * @date 2022/3/22
 */
public class Solution_56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (t1, t2) -> Integer.compare(t1[0], t2[0]));
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        int[] tmpInterval = intervals[0];
        for (int[] interval : intervals) {
            if (interval[0] <= tmpInterval[1]) {
                tmpInterval[1] = Math.max(interval[1], tmpInterval[1]);
            } else {
                tmpInterval = interval;
                result.add(interval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] tmp = {{1,3},{2,6},{8,10},{15,18}};
        Solution_56 solution_56 = new Solution_56();
        int[][] merge = solution_56.merge(tmp);
        System.out.println(merge);
    }
}
