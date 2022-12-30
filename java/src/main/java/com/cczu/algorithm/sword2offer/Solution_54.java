package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 剑指 Offer II 054. 所有大于等于节点的值之和
 * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 * <p>
 * <p>
 * <p>
 * 提醒一下，二叉搜索树满足下列约束条件：
 * <p>
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 示例 2：
 * <p>
 * 输入：root = [0,null,1]
 * 输出：[1,null,1]
 * 示例 3：
 * <p>
 * 输入：root = [1,0,2]
 * 输出：[3,3,2]
 * 示例 4：
 * <p>
 * 输入：root = [3,2,4,1]
 * 输出：[7,9,4,10]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中的节点数介于 0 和 104 之间。
 * 每个节点的值介于 -104 和 104 之间。
 * 树中的所有值 互不相同 。
 * 给定的树为二叉搜索树。
 */
public class Solution_54 {
    public TreeNode convertBST(TreeNode root) {
        /*
         * 反向中序遍历
         */
        helper(root);
        return root;
    }

    int sum = 0;

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.right);
        sum += node.val;
        node.val = sum;
        helper(node.left);
    }

    public static void main(String[] args) {
        Integer[] arr = {4,1,6,0,2,5,7,null,null,null,3,null,null,null,8};
        Solution_54 solution_54 = new Solution_54();
        System.out.println(solution_54.convertBST(TreeNode.build(arr)));
    }
}
