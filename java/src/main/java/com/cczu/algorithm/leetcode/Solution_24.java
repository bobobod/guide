package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 * <p>
 * Input: head = []
 * Output: []
 * Example 3:
 * <p>
 * Input: head = [1]
 * Output: [1]
 *
 * @author yjz
 * @date 2022/2/16
 */
public class Solution_24 {
    public static void main(String[] args) {
        Solution_24 solution_24 = new Solution_24();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode listNode = solution_24.swapPairs(head);
        System.out.println(listNode);
    }

    public ListNode swapPairs(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tHead = swapPairs(head.next.next);
        ListNode temp = head.next;
        head.next = tHead;
        temp.next = head;
        return temp;
    }

}
