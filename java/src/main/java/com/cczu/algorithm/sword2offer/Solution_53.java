package com.cczu.algorithm.sword2offer;

import com.cczu.algorithm.tree.TreeNode;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * <p>
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [2,1,3], p = 1
 * 输出：2
 * 解释：这里 1 的中序后继是 2。请注意 p 和返回值都应是 TreeNode 类型。
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], p = 6
 * 输出：null
 * 解释：因为给出的节点没有中序后继，所以答案就返回 null 了。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [1, 104] 内。
 * -105 <= Node.val <= 105
 * 树中各节点的值均保证唯一。
 */
public class Solution_53 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode result = null, cur = root;
        while (cur != null) {
            if (cur.val > p.val) {
                result = cur;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Integer[] arr = {2, 1, 3};
        TreeNode build = TreeNode.build(arr);
        Solution_53 solution_53 = new Solution_53();
        System.out.println(solution_53.inorderSuccessor(build, new TreeNode(1)));
    }
}
