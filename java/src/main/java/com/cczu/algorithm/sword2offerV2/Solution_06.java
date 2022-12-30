package com.cczu.algorithm.sword2offerV2;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead"]
 * [[],[3],[],[]]
 * 输出：[null,null,3,-1]
 * 示例 2：
 * <p>
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 */
public class Solution_06 {

    public static void main(String[] args) {
        Solution_06 solution_06 = new Solution_06();
        solution_06.appendTail(1);
        solution_06.appendTail(2);
        solution_06.appendTail(3);
        System.out.println(solution_06.deleteHead());
        System.out.println(solution_06.deleteHead());
        System.out.println(solution_06.deleteHead());
    }

    private final Stack<Integer> inStack;
    private final Stack<Integer> outStack;

    public Solution_06() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void appendTail(int value) {
        inStack.push(value);
    }

    public int deleteHead() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        if (outStack.isEmpty()) {
            return -1;
        } else {
            return outStack.pop();
        }

    }
}
