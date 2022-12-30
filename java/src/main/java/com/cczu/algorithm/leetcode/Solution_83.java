package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * <p>
 * Example 1:
 * <p>
 * Input: 1->1->2
 * Output: 1->2
 * Example 2:
 * <p>
 * Input: 1->1->2->3->3
 * Output: 1->2->3
 *
 * @author jianzhen.yin
 * @date 2020/9/27
 */
public class Solution_83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = head;
        while (head != null) {
            ListNode next = head.next;
            if (next != null && head.val == next.val){
                head.next = next.next;
            }else {
                head = head.next;
            }
        }
        return res;
    }
}
