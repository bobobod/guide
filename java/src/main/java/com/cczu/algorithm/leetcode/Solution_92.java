package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * 92. Reverse Linked List II
 * Medium
 * <p>
 * 6390
 * <p>
 * 302
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 */
public class Solution_92 {
    public static void main(String[] args) {
        Solution_92 solution_92 = new Solution_92();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println(solution_92.reverseBetween(listNode, 2, 4));
    }

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode pre = res;
        // 1. find the pre node
        for (int i = 1; i < left; i++) {
            pre = pre.next;
        }
        ListNode start = pre.next;
        ListNode then = start.next;
        // 2. 转换次数
        for (int i = 0; i < right - left; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }
        return res.next;
    }
}
