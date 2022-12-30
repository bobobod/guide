package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * 82. Remove Duplicates from Sorted List II
 * Medium
 * <p>
 * 5825
 * <p>
 * 170
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list. Return the linked list sorted as well.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,3,4,4,5]
 * Output: [1,2,5]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,1,1,2,3]
 * Output: [2,3]
 */
public class Solution_82 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode pre = res, cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == cur.val) {
                cur = cur.next;
            }
            if (pre.next == cur) {
                // 如果位置相同的话，说明元素唯一
                pre = pre.next;
            } else {
                // 如果位置不同的话，说明存在重复数据，则指向下一个元素
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
        Solution_82 solution_82 = new Solution_82();
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        System.out.println(solution_82.deleteDuplicates(listNode));
    }
}
