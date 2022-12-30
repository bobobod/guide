package com.cczu.algorithm.sword2offer;

/**
 * 剑指 Offer II 067. 最大的异或
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 * <p>
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 2 * 105
 * 0 <= nums[i] <= 231 - 1
 */
public class Solution_67 {
    Trie root = new Trie();

    public int findMaximumXOR(int[] nums) {
        // 使用前缀树实现
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            // 前i-1都添加进去了
            add(nums[i - 1]);
            // 找到最大的异或值
            max = Math.max(max, searchMax(nums[i]));
        }
        return max;
    }

    private void add(int num) {
        Trie node = root;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (node.left == null) {
                    node.left = new Trie();
                }
                node = node.left;
            } else {
                if (node.right == null) {
                    node.right = new Trie();
                }
                node = node.right;
            }
        }
    }

    private int searchMax(int num) {
        Trie node = root;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (bit == 0) {
                if (node.right != null) {
                    result = result * 2 + 1;
                    node = node.right;
                } else {
                    result = result * 2;
                    node = node.left;
                }
            } else {
                if (node.left != null) {
                    result = result * 2 + 1;
                    node = node.left;
                } else {
                    result = result * 2;
                    node = node.right;
                }
            }
        }
        return result;
    }
}

class Trie {
    // 左边存放0，右边存放1
    Trie left = null;
    Trie right = null;
}
