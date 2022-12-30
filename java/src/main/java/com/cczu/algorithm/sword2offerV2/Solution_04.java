package com.cczu.algorithm.sword2offerV2;

import com.cczu.algorithm.list.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 * <p>
 * 通过次数502,040提交次数670,349
 */
public class Solution_04 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4};
        Solution_04 solution_04 = new Solution_04();
        System.out.println(Arrays.toString(solution_04.reversePrint(ListNode.build(arr))));
    }

    public int[] reversePrint(ListNode head) {
        // 利用递归实现，递推阶段为null时终止，回溯节点依次将数据装起来
        List<Integer> tmp = new ArrayList<>();
        recursive(tmp, head);
        int[] res = new int[tmp.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = tmp.get(i);
        }
        return res;
    }

    private void recursive(List<Integer> tmp, ListNode head) {
        if (head == null) {
            return;
        }
        recursive(tmp, head.next);
        // 细节，在回溯阶段合并数据
        tmp.add(head.val);
    }
}
