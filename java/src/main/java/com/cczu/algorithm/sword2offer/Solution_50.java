package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 050. 向下的路径节点之和
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 */
public class Solution_50 {
    public int pathSum(TreeNode root, int targetSum) {
        /*
         * 前缀和的方式实现，使用map存在当前路径前的前缀和
         * node1 -> node2 -> node3 -> node4 -> node5 -> node6
         * 计算到node6的时候已经保存了前面链路的所有前缀和
         */
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);
        return dfs(root, map, 0L, targetSum);
    }

    private int dfs(TreeNode node, Map<Long, Integer> prefix, Long curr, int targetNum) {
        if (node == null) {
            return 0;
        }
        int result = 0;
        int val = node.val;
        curr += val;
        result = prefix.getOrDefault(curr - targetNum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        result += dfs(node.left, prefix, curr, targetNum);
        result += dfs(node.right, prefix, curr, targetNum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        Solution_50 solution_50 = new Solution_50();
        System.out.println(solution_50.pathSum(TreeNode.build(arr), 22));
    }
}
