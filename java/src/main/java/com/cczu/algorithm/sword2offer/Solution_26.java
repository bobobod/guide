package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.list.ListNode;

/**
 * 剑指 Offer II 026. 重排链表
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0 → L1 → … → Ln-1 → Ln
 * 请将其重新排列后变为：
 * <p>
 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4]
 * 输出: [1,4,2,3]
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,4,5]
 * 输出: [1,5,2,4,3]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class Solution_26 {
    public void reorderList(ListNode head) {
        /*
         * 解题思路：
         * 1. 找到中间节点
         * 2. 反转右边节点
         * 3. 合并两个节点（两者长度相差不超过1，可直接合并）
         */
        if (head == null || head.next == null) {
            return;
        }
        ListNode mid = findMiddle(head);
        ListNode right = reverse(mid.next);
        mid.next = null;

        mergeRight2Left(head, right);
    }

    private void mergeRight2Left(ListNode left, ListNode right) {
        ListNode leftTmp, rightTmp;
        while (left != null && right != null) {
            leftTmp = left.next;
            rightTmp = right.next;
            left.next = right;
            right.next = leftTmp;
            left = leftTmp;
            right = rightTmp;
        }
    }

    private ListNode reverse(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    private ListNode findMiddle(ListNode node) {
        // 快慢指针
        ListNode slow = node, fast = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4};
        Solution_26 solution_26 = new Solution_26();
        ListNode build = ListNode.build(arr);
        solution_26.reorderList(build);
        System.out.println(build);

    }
}
