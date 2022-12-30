package com.cczu.algorithm;

import java.util.Scanner;

/**
 * least recently used
 * 最近最少使用算法
 * @author jianzhen.yin
 * @date 2020/10/28
 */
public class LRU<T> {
    /**
     * 默认容量
     */
    private static final Integer DEFAULT_CAPACITY = 10;
    /**
     * 头节点
     */
    private SNode<T> headNode;
    /**
     * 链表长度
     */
    private Integer length;
    /**
     * 链表容量
     */
    private Integer capacity;

    public LRU() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRU(Integer capacity) {
        this.headNode = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void add(T data){
        SNode preNode = findPreNode(data);
        // 判断前置节点是否为空
       if (preNode != null){
           deleteNextElement(preNode);
           insertElement2head(data);
       }else {
           if (length >= this.capacity){
               deleteEndElement();
           }
           insertElement2head(data);
       }
    }

    /**
     * 删除尾节点
     */
    private void deleteEndElement() {
        SNode node = headNode;
        // 空链表直接返回
        if (node.getNext() == null){
            return;
        }
        // 倒数第二哥节点
        while (node.getNext().getNext() != null){
            node = node.getNext();
        }
        node.setNext(null);

        length --;
    }
    public void printAll(){
        SNode head = headNode.getNext();
        while (head != null){
            System.out.print(head.getElement()+"-");
            head = head.getNext();
        }
        System.out.println();
    }
    /**
     * 插入节点到节点头
     *
     * @param data
     */
    private void insertElement2head(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data,next));
        length ++;
    }


    /**
     * 删除下一个节点
     * @param preNode
     */
    private void deleteNextElement(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length --;
    }

    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null){
            if (node.getNext().getElement().equals(data)){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    public class SNode<T>{
        private T element;
        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }

        public SNode(){
            this.next = null;
        }
    }

    public static void main(String[] args) {
        LRU<Integer> lru = new LRU<>();
        Scanner scanner = new Scanner(System.in);
        while (true){
            lru.add(scanner.nextInt());
            lru.printAll();
        }
    }
}
