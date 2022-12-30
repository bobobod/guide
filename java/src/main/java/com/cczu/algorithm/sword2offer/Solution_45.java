package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * <p>
 * 假设二叉树中至少有一个节点。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * 输入: root = [2,1,3]
 * 输出: 1
 * 示例 2:
 * <p>
 * <p>
 * <p>
 * 输入: [1,2,3,4,null,5,6,null,null,7]
 * 输出: 7
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [1,104]
 * -231 <= Node.val <= 231 - 1
 */
public class Solution_45 {
    public int findBottomLeftValue(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result, 0);
        return result.get(result.size() - 1);
    }

    private void helper(TreeNode node, List<Integer> result, int level) {
        if (node == null) {
            return;
        }
        if (result.size() == level) {
            result.add(node.val);
        }
        helper(node.left, result, level + 1);
        helper(node.right, result, level + 1);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, null, 5, 6, null, null, 7};
        Solution_45 solution_45 = new Solution_45();
        System.out.println(solution_45.findBottomLeftValue(TreeNode.build(arr)));
    }
}
