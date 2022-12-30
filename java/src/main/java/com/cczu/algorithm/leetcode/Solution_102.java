package com.cczu.algorithm.leetcode;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_102 {
    /**
     * 广度优先搜索
     *
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        traversal(res,root,0);
        return res;
    }

    public void traversal(List<List<Integer>> res, TreeNode root, int level){
        if(root == null){
            return;
        }
        List<Integer> list = null;
        if (level < res.size()){
            list = res.get(level);
        }else {
            list = new ArrayList<>();
            res.add(list);
        }
        list.add(root.val);
        traversal(res,root.left,level + 1);
        traversal(res,root.right,level + 1);
    }
}
