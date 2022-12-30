package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 100. Same Tree
 * Easy
 * <p>
 * Add to List
 * Share
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * Example 1:
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Example 2:
 * Input: p = [1,2], q = [1,null,2]
 * Output: false
 * Example 3:
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 */
public class Solution_100 {
    public static void main(String[] args) {
        Integer[] arr1 ={1,2,3};
        Integer[] arr2 ={1,2};
        TreeNode p = TreeNode.build(arr1);
        TreeNode q = TreeNode.build(arr2);
        Solution_100 solution_100 = new Solution_100();
        System.out.println(solution_100.isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
