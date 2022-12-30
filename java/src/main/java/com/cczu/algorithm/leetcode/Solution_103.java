package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Medium
 * <p>
 * 6437
 * <p>
 * 181
 * <p>
 * Add to List
 * <p>
 * Share
 * Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[20,9],[15,7]]
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 */
public class Solution_103 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }
        List<Integer> tmp;
        if (level < res.size()) {
            tmp = res.get(level);
        } else {
            tmp = new ArrayList<>();
            res.add(tmp);
        }
        if (level % 2 == 0) {
            // 往后插入元素
            tmp.add(root.val);
        } else {
            // 往前插入元素
            tmp.add(0, root.val);
        }
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, null, null, 5};
        TreeNode treeNode = TreeNode.build(arr);

        Solution_103 solution_103 = new Solution_103();
        System.out.println(solution_103.zigzagLevelOrder(treeNode));
    }
}
