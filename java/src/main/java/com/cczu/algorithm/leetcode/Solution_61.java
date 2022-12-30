package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.list.ListNode;

/**
 * 61. Rotate List
 * Medium
 * <p>
 * 4896
 * <p>
 * 1263
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the head of a linked list, rotate the list to the right by k places.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [4,5,1,2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [0,1,2], k = 4
 * Output: [2,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 500].
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * @author yjz
 * @date 2022/4/1
 */
public class Solution_61 {
    public static void main(String[] args) {
        ListNode root = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        Solution_61 solution_61 = new Solution_61();
        System.out.println(solution_61.rotateRight(root,2));
    }

    public ListNode rotateRight(ListNode head, int k) {
        // 1. 先计算长度 2. 获取 len - k%len  的节点链路  3. rotate
        if (head == null || head.next == null) {
            return head;
        }
        // 站点打工仔
        ListNode tmp = new ListNode(0);
        tmp.next = head;
        ListNode lenCompute = tmp, movedNode = tmp;
        // 获取节点长度
        int len = 0;
        for (; lenCompute.next != null; len++) {
            lenCompute = lenCompute.next;
        }
        // get rotateNode
        for (int i = len - k % len; i > 0; i--) {
            movedNode = movedNode.next;
        }
        lenCompute.next = tmp.next;
        tmp.next = movedNode.next;
        movedNode.next = null;
        return tmp.next;
    }
}
