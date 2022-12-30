package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.list.ListNode;

/**
 * 剑指 Offer II 027. 回文链表
 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
 * <p>
 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2,3,3,2,1]
 * 输出: true
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入: head = [1,2]
 * 输出: false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 链表 L 的长度范围为 [1, 105]
 * 0 <= node.val <= 9
 * <p>
 * <p>
 * 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class Solution_27 {
    ListNode frontNode;

    public boolean isPalindrome(ListNode head) {
        /*
         * 使用递归来解题,利用递归的特性来做
         */
        frontNode = head;
        return helper(head);

    }

    private boolean helper(ListNode currentNode) {
        if (currentNode != null) {
            if (!helper(currentNode.next)) {
                return false;
            }
            if (frontNode.val != currentNode.val) {
                return false;
            }
            frontNode = frontNode.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 3, 2, 1};
        ListNode build = ListNode.build(arr);
        Solution_27 solution_27 = new Solution_27();
        System.out.println(solution_27.isPalindrome(build));
    }
}
