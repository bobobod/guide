package com.cczu.algorithm.tree;

import java.util.*;

/**
 * @author jianzhen.yin
 * @date 2020/9/28
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public int getVal() {
        return val;
    }

    @Override
    public String toString() {
        return "TreeNode{" + "val=" + val + ", left=" + left + ", right=" + right + '}';
    }

    /**
     * 构建二叉树
     *
     * @param arr 数组 arr
     * @return 二叉树
     */
    public static TreeNode build(Integer[] arr) {
        return helper(arr, 0);
    }

    private static TreeNode helper(Integer[] arr, int index) {
        TreeNode tree = null;
        if (index < arr.length) {
            if (arr[index] == null) {
                return null;
            }
            tree = new TreeNode(arr[index]);
            tree.left = helper(arr, 2 * index + 1);
            tree.right = helper(arr, 2 * index + 2);
        }
        return tree;
    }

    /**
     * 深度遍历，利用栈的特性先进后出来实现
     *
     * @param root 父节点
     * @return 深度遍历集合
     */
    public static List<Integer> dfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node == null) {
                continue;
            }
            result.add(node.val);
            // 先push右子树
            stack.push(node.right);
            stack.push(node.left);
        }
        return result;
    }

    /**
     * 广度遍历，利用队列的特性  先进先出
     *
     * @param root 父节点
     * @return 广度遍历集合
     */
    public static List<Integer> bfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 每次取出第一个元素，从尾部加入，首部取出
            TreeNode node = queue.poll();
            if (node == null) {
                continue;
            }
            result.add(node.val);
            // 先填加左子树
            queue.add(node.left);
            queue.add(node.right);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, null, 5, null, 4};
        TreeNode treeNode = TreeNode.build(arr);
        System.out.println(bfs(treeNode));
        System.out.println(dfs(treeNode));
    }


}
