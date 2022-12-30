package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 95. Unique Binary Search Trees II
 * Medium
 * <p>
 * 4864
 * <p>
 * 327
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 * <p>
 * Input: n = 1
 * Output: [[1]]
 */
public class Solution_95 {
    public static void main(String[] args) {
        Solution_95 solution_95 = new Solution_95();
        System.out.println(solution_95.generateTrees(4));
    }

    public List<TreeNode> generateTrees(int n) {
        return generateSubTrees(1, n);
    }

    private List<TreeNode> generateSubTrees(int s, int e) {
        List<TreeNode> res = new ArrayList<>();
        if (s > e) {
            res.add(null);
            return res;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> leftTree = generateSubTrees(s, i - 1);
            List<TreeNode> rightTree = generateSubTrees(i + 1, e);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;
                    res.add(treeNode);
                }
            }
        }
        return res;
    }
}
