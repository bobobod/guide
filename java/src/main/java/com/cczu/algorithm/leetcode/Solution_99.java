package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 99. Recover Binary Search Tree
 * Medium
 * <p>
 * 5500
 * <p>
 * 186
 * <p>
 * Add to List
 * <p>
 * Share
 * You are given the root of a binary search tree (BST), where the values of exactly two nodes of the tree were swapped by mistake. Recover the tree without changing its structure.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,3,null,null,2]
 * Output: [3,1,null,null,2]
 * Explanation: 3 cannot be a left child of 1 because 3 > 1. Swapping 1 and 3 makes the BST valid.
 * Example 2:
 * <p>
 * <p>
 * Input: root = [3,1,4,null,null,2]
 * Output: [2,1,4,null,null,3]
 * Explanation: 2 cannot be in the right subtree of 3 because 2 < 3. Swapping 2 and 3 makes the BST valid.
 */
public class Solution_99 {
    // record the first node which needs swap
    TreeNode firstNode = null;
    // record the second node which needs swap
    TreeNode secondNode = null;
    // prev node
    TreeNode prevNode = null;

    public void recoverTree(TreeNode root) {
        /*
         * 适用 中序遍历遍历tree，6，3，4，5，2
         * 定义两个变量找出 第一个 6 和 第二个 2
         * 两者进行交换
         */
        traverse(root);
        // swap
        int tmp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tmp;
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        // do something
        if (firstNode == null && (prevNode == null || prevNode.val > root.val)) {
            firstNode = prevNode;
        }
        if (firstNode != null && prevNode.val > root.val) {
            secondNode = root;
        }
        prevNode = root;
        traverse(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(3, null, new TreeNode(2, null, null)), null);
        Solution_99 solution_99 = new Solution_99();
        solution_99.recoverTree(root);
        System.out.println(root);
    }
}
