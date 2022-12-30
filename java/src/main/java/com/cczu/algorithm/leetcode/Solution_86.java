package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 * <p>
 * You should preserve the original relative order of the nodes in each of the two partitions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,4,3,2,5,2], x = 3
 * Output: [1,2,2,4,3,5]
 * Example 2:
 * <p>
 * Input: head = [2,1], x = 2
 * Output: [1,2]
 */
public class Solution_86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode();
        res.next = head;
        ListNode cur = head, pre = res, p = res;
        while (cur != null) {
            if (cur.val < x) {
                if (pre.next == cur) {
                    pre = cur;
                } else {
                    p.next = cur.next;

                    ListNode tmp = pre.next;
                    pre.next = cur;
                    pre.next.next = tmp;
                    pre = cur;
                    cur = p;
                }
            }
            p = cur;
            cur = cur.next;
        }
        return res.next;
    }

    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5, new ListNode(2, null))))));
        ListNode listNode = new ListNode(2, new ListNode(1, null));
        Solution_86 solution_86 = new Solution_86();
        System.out.println(solution_86.partition(listNode, 2));
    }
}
