package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *     2
 *    / \
 *   1   3
 *
 * Input: [2,1,3]
 * Output: true
 * Example 2:
 *
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 *
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 * @author jianzhen.yin
 * @date 2020/9/30
 */
public class Solution_98 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return valid(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean valid(TreeNode root, long minValue, long maxValue) {
        if (root == null){
            return true;
        }
        if (minValue >= root.val || maxValue <= root.val){
            return false;
        }
        return valid(root.left,minValue,root.val) && valid(root.right,root.val,maxValue);
    }

}
