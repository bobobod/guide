package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 * <p>
 * Input: head = []
 * Output: []
 *
 * @author yjz
 * @date 2022/2/23
 */
public class Solution_206 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3)));
        Solution_206 solution_206 = new Solution_206();
        ListNode listNode1 = solution_206.reverseList(listNode);
        System.out.println(listNode1);
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 取出除去第一个所有尾部数据
        ListNode tail = head.next;
        //去除头部后面关联的元素
        head.next = null;
        // 反转尾部数据
        ListNode newHead = reverseList(tail);
        // 指向第一个元素
        tail.next = head;
        return newHead;
    }
}
