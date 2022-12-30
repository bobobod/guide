package com.cczu.algorithm.sword2offerV2;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 剑指 Offer 07. 重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * <p>
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class Solution_05 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }

        return helper(preorder, inorder, 0, preorder.length - 1, 0);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int start, int end, int k) {
        if (start > end || k > preorder.length - 1) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[k]);
        int i = start;
        for (; i <= end; i++) {
            if (inorder[i] == preorder[k]) {
                break;
            }
        }
        // 按照树的前序遍历可得  k + 1 就是下一个左子树节点
        root.left = helper(preorder, inorder, start, i - 1, k + 1);
        // 第一个右子树节点是   k + 左子树长度（ i - start + 1 )
        root.right = helper(preorder, inorder, i + 1, end, k + i - start + 1);

        return root;
    }

    public static void main(String[] args) {
        Solution_05 solution_05 = new Solution_05();
        int[] preOrder = {3, 9, 7, 8, 20, 15, 7};
        int[] inOrder = {7, 9, 8, 3, 15, 20, 7};
        System.out.println(solution_05.buildTree(preOrder, inOrder));
    }
}
