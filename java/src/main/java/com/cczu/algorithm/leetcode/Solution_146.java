package com.cczu.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;


/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 * <p>
 * Implement the LRUCache class:
 * <p>
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 */
public class Solution_146 {
    static class Node {
        int key;
        int value;
        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    private final Node head;
    private final Node tail;
    private final int capacity;
    private int count;
    private final Map<Integer, Node> map;


    public Solution_146(int capacity) {
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        this.capacity = capacity;
        this.count = 0;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 更新node, 保持最前的是最近使用的，最后的是最老的，与linkedHashMap相反
        update(node);
        return node.value;
    }

    public void update(Node node) {
        // 删除数据
        remove(node);
        // 新增到最前面
        add(node);
    }

    private void add(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
    }

    private void remove(Node node) {
        Node pre = node.pre;
        pre.next = node.next;
        node.next.pre = pre;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, value);
            map.put(key, node);
            add(node);
            this.count++;
        } else {
            // 更新元素值
            node.value = value;
            update(node);
        }
        if (count > capacity) {
            // 删除尾部元素
            Node pre = tail.pre;
            map.remove(pre.key);
            remove(pre);
            this.count--;
        }
    }

    public static void main(String[] args) {
        Solution_146 solution_146 = new Solution_146(3);
        solution_146.put(1, 1);
        solution_146.put(2, 2);
        solution_146.put(3, 3);
        System.out.println(solution_146.map);
        solution_146.put(4, 4);
        System.out.println(solution_146.map);
    }
}
