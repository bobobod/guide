package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer II 044. 二叉树每层的最大值
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 * <p>
 * <p>
 * <p>
 * 示例1：
 * <p>
 * 输入: root = [1,3,2,5,3,null,9]
 * 输出: [1,3,9]
 * 解释:
 * 1
 * / \
 * 3   2
 * / \   \
 * 5   3   9
 * 示例2：
 * <p>
 * 输入: root = [1,2,3]
 * 输出: [1,3]
 * 解释:
 * 1
 * / \
 * 2   3
 * 示例3：
 * <p>
 * 输入: root = [1]
 * 输出: [1]
 * 示例4：
 * <p>
 * 输入: root = [1,null,2]
 * 输出: [1,2]
 * 解释:
 * 1
 * \
 * 2
 * 示例5：
 * <p>
 * 输入: root = []
 * 输出: []
 * <p>
 * <p>
 * 提示：
 * <p>
 * 二叉树的节点个数的范围是 [0,104]
 * -231 <= Node.val <= 231 - 1
 */
public class Solution_44 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result, 0);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result, int level) {
        if (node == null) {
            return;
        }
        int val = node.val;
        if (result.size()  == level) {
            result.add(val);
        } else {
            int prev = result.get(level);
            result.remove(level);
            result.add(level, Math.max(prev, val));
        }
        helper(node.left, result, level + 1);
        helper(node.right, result, level + 1);
    }

    public static void main(String[] args) {
        Integer[] arr = {1,3,2,5,3,null,9};
        Solution_44 solution_44 = new Solution_44();
        System.out.println(solution_44.largestValues(TreeNode.build(arr)));
    }
}
