package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 052. 展平二叉搜索树
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，
 * 并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,8,1,null,null,null,7,9]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,1,7]
 * 输出：[1,null,5,null,7]
 */
public class Solution_52 {
    public TreeNode increasingBST(TreeNode root) {

        TreeNode result = new TreeNode(), cur = result;
        List<Integer> values = new ArrayList<>();
        helper(root, values);
        for (int i = 0; i < values.size(); i++) {
            cur.right = new TreeNode(values.get(i));
            cur = cur.right;
        }
        return result.right;
    }

    private void helper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        helper(node.left, result);
        result.add(node.val);
        helper(node.right, result);

    }

}
