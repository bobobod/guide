package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * Follow up: Can you solve it with time complexity O(height of tree)?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [5,3,6,2,4,null,7], key = 3
 * Output: [5,4,6,2,null,null,7]
 * Explanation: Given key to delete is 3. So we find the node with value 3 and delete it.
 * One valid answer is [5,4,6,2,null,null,7], shown in the above BST.
 * Please notice that another valid answer is [5,2,6,null,4,null,7] and it's also accepted.
 *
 * Example 2:
 *
 * Input: root = [5,3,6,2,4,null,7], key = 0
 * Output: [5,3,6,2,4,null,7]
 * Explanation: The tree does not contain a node with value = 0.
 * Example 3:
 *
 * Input: root = [], key = 0
 * Output: []
 *
 * @author jianzhen.yin
 * @date 2020/9/30
 */
public class Solution_450 {
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null){
            return null;
        }
        if (root.val > key){
            root.left = deleteNode(root.left, key);
            return root;
        }
        if (root.val < key){
            root.right = deleteNode(root.right, key);
            return root;
        }
        if (root.left == null){
            return root.right;
        }
        if (root.right == null){
            return root.left;
        }
        // 后继
        TreeNode minNode = root.right;
        while (minNode.left != null){
            minNode = minNode.left;
        }
        root.right = deleteMinNode(root.right);
        root.val = minNode.val;
        return root;
    }

    private TreeNode deleteMinNode(TreeNode root) {
        if (root.left == null){
            TreeNode pRight = root.right;
            root.right = null;
            return pRight;
        }
        root.left = deleteMinNode(root.left);
        return root;
    }
}
