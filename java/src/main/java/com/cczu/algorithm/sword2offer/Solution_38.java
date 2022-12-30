package com.cczu.algorithm.sword2offer;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer II 038. 每日温度
 * 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 */
public class Solution_38 {
    public int[] dailyTemperatures(int[] temperatures) {
        /*
         * 单调栈实现，维护一个单调递减的温度序列
         */
        int length = temperatures.length;
        int[] res = new int[length];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            while (!deque.isEmpty() && temperatures[i] > temperatures[deque.peek()]) {
                Integer prevIndex = deque.pop();
                res[prevIndex] = i - prevIndex;
            }
            deque.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {73, 74, 75, 71, 69, 72, 76, 73};
        Solution_38 solution_38 = new Solution_38();
        System.out.println(Arrays.toString(solution_38.dailyTemperatures(arr)));
    }
}
