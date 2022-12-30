package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 056. 二叉搜索树中两个节点之和
 * 给定一个二叉搜索树的 根节点 root 和一个整数 k , 请判断该二叉搜索树中是否存在两个节点它们的值之和等于 k 。假设二叉搜索树中节点的值均唯一。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 12
 * 输出: true
 * 解释: 节点 5 和节点 7 之和等于 12
 * 示例 2：
 * <p>
 * 输入: root = [8,6,10,5,7,9,11], k = 22
 * 输出: false
 * 解释: 不存在两个节点值之和为 22 的节点
 */
public class Solution_56 {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nodeValues = new ArrayList<>();
        inorder(root, nodeValues);
        int start = 0, end = nodeValues.size() - 1;
        while (start < end) {
            int sum = nodeValues.get(start) + nodeValues.get(end);
            if (sum == k) {
                return true;
            } else if (sum > k) {
                end--;
            } else {
                start++;
            }
        }
        return false;
    }

    public void inorder(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inorder(node.left, result);
        result.add(node.val);
        inorder(node.right, result);
    }
}
