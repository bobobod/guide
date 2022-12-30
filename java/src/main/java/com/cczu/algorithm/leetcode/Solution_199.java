package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 199. Binary Tree Right Side View
 * Medium
 * <p>
 * 8159
 * <p>
 * 472
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,null,5,null,4]
 * Output: [1,3,4]
 * Example 2:
 * <p>
 * Input: root = [1,null,3]
 * Output: [1,3]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 */
public class Solution_199 {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, null, 5, null, 4};
        TreeNode treeNode = TreeNode.build(arr);
        Solution_199 solution_199 = new Solution_199();
        List<Integer> integers = solution_199.rightSideView(treeNode);
        System.out.println(integers);
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(result, root, 1);
        return result;
    }

    private void helper(List<Integer> result, TreeNode node, int level) {
        if (node == null) {
            return;
        }
        if (result.size() == level - 1) {
            result.add(node.val);
        }
        helper(result, node.right, level + 1);
        helper(result, node.left, level + 1);
    }
}
