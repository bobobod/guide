package com.cczu.algorithm.leetcode;

import com.alibaba.fastjson.JSONObject;
import com.cczu.algorithm.tree.TreeNode;

import java.io.UnsupportedEncodingException;

/**
 * Given a binary tree, find its maximum depth.
 * <p>
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * <p>
 * Note: A leaf is a node with no children.
 * <p>
 * Example:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * return its depth = 3.
 *
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class Solution_104 {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        /*
        以utf8为例，utf8是一个变长编码标准，可以以1~4个字节表示一个字符，而中文占3个字节，ascII字符占1个字节。
    那么为什么我们在java里面可以用一个char来表示一个中文呢？
    因为java是以unicode作为编码方式的。unicode是一个定长的编码标准，每个字符都是2个字节，也就是1个char类型的空间。
    在编译时会把utf8的中文字符转换成对应的unicode来进行传输运算。
         */
        System.out.println("中a".getBytes("unicode").length);
        JSONObject object = new JSONObject();
        object.put("sss",111);
        System.out.println(Long.parseLong(object.getString("sss")));

    }

}
