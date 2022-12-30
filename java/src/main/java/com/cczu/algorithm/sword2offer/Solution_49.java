package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 剑指 Offer II 049. 从根节点到叶节点的路径数字之和
 * 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * <p>
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 */
public class Solution_49 {
    public int sumNumbers(TreeNode root) {
        return helper(root, 0);
    }

    private int helper(TreeNode node, int curSum) {
        if (node == null) {
            return 0;
        }
        int prevSum = curSum * 10 + node.val;
        if (node.left == null && node.right == null) {
            // 为叶子节点，则直接返回
            return prevSum;
        } else {
            // 非叶子节点
            return helper(node.left, prevSum) + helper(node.right, prevSum);
        }
    }

    public static void main(String[] args) {
        System.out.println((byte) 128); // 转换cheng byte位后再判断
        System.out.println((byte) 129);
        System.out.println((byte) (100 * 3));
        Integer[] arr = {4, 9, 0, 5, 1};
        Solution_49 solution_49 = new Solution_49();
        System.out.println(solution_49.sumNumbers(TreeNode.build(arr)));
    }
}
