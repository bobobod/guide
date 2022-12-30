package com.cczu.algorithm.stack;

/**
 * @author jianzhen.yin
 * @date 2020/11/1
 */
public class StackBasedOnLinkedList {
    private Node head;
    public void push(String data){
        Node node = head = new Node(data);
        if (head == null){
            head = node;
        }else {
            node.next = head;
            head = node;
        }
    }
    public String pop(){
        String date = null;
        if (head != null){
            date = head.getDate();
            head = head.next;
        }
        return date;
    }

    private static class Node{
        private String date;
        private Node next;

        public Node(String date, Node next) {
            this.date = date;
            this.next = next;
        }

        public Node(String date) {
            this.date = date;
            this.next = null;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    public void printAll(){
        Node cur = head;
        if (cur != null){
            System.out.print(cur.getDate()+"-");
        }
    }

    public static void main(String[] args) {

    }
}
