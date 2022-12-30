package com.cczu.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * 57. Insert Interval
 * Medium
 * <p>
 * 4530
 * <p>
 * 328
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
 * <p>
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 * <p>
 * Return intervals after the insertion.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 *
 * @author yjz
 * @date 2022/3/26
 */
public class Solution_57 {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0;
        List<int[]> result = new ArrayList<>();
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        int start = newInterval[0];
        int end = newInterval[1];
        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            start = Math.min(intervals[i][0], start);
            end = Math.max(intervals[i][1], end);
            i++;
        }
        int[] tmp = {start, end};
        result.add(tmp);

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 5}, {9, 10}};
        int[] newInterval = {6, 9};
        Solution_57 solution_57 = new Solution_57();
        int[][] insert = solution_57.insert(intervals, newInterval);
        for (int[] ints : insert) {
            Stream.of(ints).forEach(i -> System.out.println(i[0] + " " + i[1]));
        }
    }
}
