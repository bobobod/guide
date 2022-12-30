package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.list.ListNode;

/**
 * 剑指 Offer II 025. 链表中的两数相加
 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * <p>
 * <p>
 * 示例1：
 * <p>
 * <p>
 * <p>
 * 输入：l1 = [7,2,4,3], l2 = [5,6,4]
 * 输出：[7,8,0,7]
 * 示例2：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[8,0,7]
 * 示例3：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表的长度范围为 [1, 100]
 * 0 <= node.val <= 9
 * 输入数据保证链表代表的数字无前导 0
 */
public class Solution_25 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseNode1 = reverseNode(l1);
        ListNode reverseNode2 = reverseNode(l2);
        ListNode result = new ListNode(), cur = result;
        int carry = 0;
        while (reverseNode1 != null || reverseNode2 != null) {
            int val1 = 0;
            int val2 = 0;
            if (reverseNode1 != null) {
                val1 = reverseNode1.val;
                reverseNode1 = reverseNode1.next;
            }
            if (reverseNode2 != null) {
                val2 = reverseNode2.val;
                reverseNode2 = reverseNode2.next;
            }
            int sum = val1 + val2 + carry;
            int newVal = sum % 10;
            carry = sum / 10;
            cur.next = new ListNode(newVal);
            cur = cur.next;
        }

        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return reverseNode(result.next);
    }

    private ListNode reverseNode(ListNode node) {
        ListNode prev = null;
        while (node != null) {
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1};
        Integer[] arr2 = {9, 9};
        Solution_25 solution_25 = new Solution_25();
        System.out.println(solution_25.addTwoNumbers(ListNode.build(arr1), ListNode.build(arr2)));

    }

}
