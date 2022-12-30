package com.cczu.algorithm.list;

import java.util.Arrays;

public class SkipList {
    /**
     * 最大层数
     */
    private static int DEFAULT_MAX_LEVEL = 32;

    /**
     * 随机层数概率 层数的起始号为1
     */
    private static double DEFAULT_P_FACTOR = 0.25;
    /**
     * 头节点
     */
    Node head = new Node(null, DEFAULT_MAX_LEVEL);
    /**
     * 当前nodes的实际层数 从1开始
     */
    int currentLevel = 1;

    public SkipList() {

    }

    /**
     * 获取随机层数
     *
     * @return
     */
    private static int randomLevel() {
        int level = 1;
        while (Math.random() < DEFAULT_P_FACTOR && level < DEFAULT_MAX_LEVEL) {
            level++;
        }
        return level;
    }

    /**
     * 从最高层开始往下找
     *
     * @param target
     * @return
     */
    public boolean search(int target) {
        Node searchNode = head;
        for (int i = currentLevel - 1; i > 0; i--) {
            searchNode = findClosest(searchNode, i, target);
            if (searchNode != null && searchNode.next[i].value == target) {
                return true;
            }
        }
        return false;
    }

    /**
     * 插入元素
     * @param num
     */
    public void add(int num) {
        int level = randomLevel();
        Node updateNode = head;
        Node newNode = new Node(num, level);
        // 计算出当前num 索引的实际层数，从该层进行索引
        for (int i = currentLevel - 1; i >= 0; i--) {
            // 插入最近的节点
            updateNode = findClosest(updateNode, i, num);
            if (i < level) {
                if (updateNode.next[i] == null) {
                    updateNode.next[i] = newNode;
                } else {
                    Node tmp = updateNode.next[i];
                    updateNode.next[i] = newNode;
                    newNode.next[i] = tmp;
                }
            }
        }
        // 如果随机出来的level比当前层数还大，将超过currentLevel的Head直接指向newNode
        if (level > currentLevel) {
            for (int i = currentLevel; i < level; i++) {
                head.next[i] = newNode;
            }
            currentLevel = level;
        }
    }

    /**
     * 删除指定元素
     *
     * @param num
     * @return
     */
    public boolean delete(int num) {
        boolean flag = false;
        Node searchNode = head;
        for (int i = currentLevel - 1; i >= 0; i--) {
            searchNode = findClosest(searchNode, i, num);
            if (searchNode.next[i] != null && searchNode.next[i].value == num) {
                searchNode.next[i] = searchNode.next[i].next[i];
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 查询level层大于node的节点 查出来的节点要么是大于等于指定值的，要不为空
     *
     * @param node
     * @param levelIndex
     * @param target
     * @return
     */
    private Node findClosest(Node node, int levelIndex, int target) {
        while (node.next[levelIndex] != null && target > node.next[levelIndex].value) {
            node = node.next[levelIndex];
        }
        return node;
    }


    class Node {
        Integer value;
        Node[] next;

        public Node(Integer value, int size) {
            this.value = value;
            next = new Node[size];
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + Arrays.toString(next) +
                    '}';
        }
    }



    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 0; i < 10; i++) {
            skipList.add((int) (Math.random() * 100) + i);
        }
        System.out.println("skipList");
    }
}
