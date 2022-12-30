package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution_46 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        helper(root, result, 0);
        return result;
    }

    private void helper(TreeNode node, List<Integer> result, int level) {
        if (node == null) {
            return;
        }
        if (result.size() == level) {
            result.add(node.val);
        }
        helper(node.right, result, level + 1);
        helper(node.left, result, level + 1);
    }
}
