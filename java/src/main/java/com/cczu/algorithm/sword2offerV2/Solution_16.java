package com.cczu.algorithm.sword2offerV2;

import com.cczu.algorithm.list.ListNode;

/**
 * 剑指 Offer 18. 删除链表的节点
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2:
 * <p>
 * 输入: head = [4,5,1,9], val = 1
 * 输出: [4,5,9]
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 */
public class Solution_16 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode root = new ListNode(), cur = head, prev = root;
        root.next = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                break;
            }
            cur = cur.next;
            prev = prev.next;
        }
        return root.next;
    }

    public static void main(String[] args) {
        Integer[] arr = {4, 5, 1, 9};
        ListNode build = ListNode.build(arr);
        Solution_16 solution_16 = new Solution_16();
        System.out.println(solution_16.deleteNode(build, 1));
    }
}
