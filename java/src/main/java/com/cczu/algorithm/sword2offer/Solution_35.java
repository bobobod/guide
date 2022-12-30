package com.cczu.algorithm.sword2offer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 剑指 Offer II 035. 最小时间差
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 2 <= timePoints <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 */
public class Solution_35 {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int result = Integer.MAX_VALUE;
        int prev = getMinute(timePoints.get(0));
        for (int i = 1; i < timePoints.size(); i++) {
            int curr = getMinute(timePoints.get(i));
            result = Math.min(result, curr - prev);
            prev = curr;
        }
        // 特殊情况是首页相差,比如 23:59  00:00
        result = Math.min(result, getMinute(timePoints.get(0)) + 24 * 60 - prev);
        return result;
    }

    private int getMinute(String time) {
        return ((time.charAt(0) - '0') * 10 + time.charAt(1) - '0') * 60 + (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
    }

    public static void main(String[] args) {
        Solution_35 solution_35 = new Solution_35();
        List<String> arr = Arrays.asList("23:59", "00:00");
        System.out.println(solution_35.findMinDifference(arr));
    }
}
