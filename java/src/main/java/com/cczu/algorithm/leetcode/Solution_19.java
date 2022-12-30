package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 *
 * Example:
 *
 * Given linked list: 1->2->3->4->5, and n = 2.
 *
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 *
 * Given n will always be valid.
 *
 * Follow up:
 *
 * Could you do this in one pass?
 * @author jianzhen.yin
 * @date 2020/9/27
 */
public class Solution_19 {
    // 双指针
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode(0,head);
        ListNode cur = result;
        for (int i = 0; i < n - 1; i++) {
            head = head.next;
        }
        while(head.next != null){
            head = head.next;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return result.next;
    }
}
