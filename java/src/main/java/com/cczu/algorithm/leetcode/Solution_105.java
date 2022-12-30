package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

/**
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal
 * of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * Example 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class Solution_105 {
    public static void main(String[] args) {
        Solution_105 solution_105 = new Solution_105();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(solution_105.buildTree(preorder, inorder));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        // 找到当前节点在中序遍历里的位置
        int curIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == root.val) {
                curIndex = i;
                break;
            }
        }
        // 下一个是左子树的第一个元素
        root.left = helper(preStart + 1, inStart, curIndex - 1, preorder, inorder);
        // 减去左子树的长度再加一就是右子树第一的数值
        root.right = helper(preStart + curIndex - inStart + 1, curIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}
