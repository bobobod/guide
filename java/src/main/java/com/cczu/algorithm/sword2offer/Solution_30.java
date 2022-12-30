package com.cczu.algorithm.sword2offer;

import java.util.*;

/**
 * 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 * <p>
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 * <p>
 * <p>
 * 示例 :
 * <p>
 * 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出: [null, true, false, true, 2, true, false, 2]
 * 解释:
 * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 * <p>
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 * <p>
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 * <p>
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 * <p>
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 * <p>
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 * <p>
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 * <p>
 * <p>
 * 提示：
 * <p>
 * -231 <= val <= 231 - 1
 * 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
 * 当调用 getRandom 方法时，集合中至少有一个元素
 * <p>
 * <p>
 * 注意：本题与主站 380 题相同：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 */
public class Solution_30 {
    public static void main(String[] args) {
        /*
         * map可以实现查找和删除是o(1)的时间复杂度，但是无法实现随机访问
         * 采用：map + 变长数组
         */
        RandomizedSet randomizedSet = new RandomizedSet();
        System.out.println(randomizedSet.insert(1));
        System.out.println(randomizedSet.remove(2));
        System.out.println(randomizedSet.insert(2));
        System.out.println(randomizedSet.getRandom());
        System.out.println(randomizedSet.remove(1));

    }

}

class RandomizedSet {
    Random random;
    List<Integer> nums;
    Map<Integer, Integer> indices;

    /**
     * Initialize your data structure here.
     */
    public RandomizedSet() {
        random = new Random();
        nums = new ArrayList<>();
        indices = new HashMap<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        // 删除这个数据，并且修改最后一个数的索引
        Integer index = indices.get(val);
        Integer last = nums.get(nums.size() - 1);
        indices.put(last, index);
        indices.remove(val);
        // index
        nums.set(index, last);
        nums.remove(nums.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        int rand = random.nextInt(nums.size());
        return nums.get(rand);
    }
}
