package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 剑指 Offer II 047. 二叉树剪枝
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * <p>
 * 节点 node 的子树为 node 本身，以及所有 node 的后代。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,null,0,0,1]
 * 输出: [1,null,0,null,1]
 * 解释:
 * 只有红色节点满足条件“所有不包含 1 的子树”。
 * 右图为返回的答案。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,0,1,0,0,0,1]
 * 输出: [1,null,1,null,1]
 * 解释:
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * 输入: [1,1,0,1,1,0,1,0]
 * 输出: [1,1,0,1,1,null,1]
 * 解释:
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [1,200]
 * 二叉树节点的值只会是 0 或 1
 */
public class Solution_47 {
    public TreeNode pruneTree(TreeNode root) {
        /*
         * 利用递归的特性解题，反向递推
         */
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }


}
