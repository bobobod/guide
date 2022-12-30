package com.cczu.algorithm.queue;

/**
 * @author jianzhen.yin
 * @date 2020/11/1
 */
public class QueueBasedOnArray {
    private String[] items;
    private int n = 0;
    private int head = 0;
    private int tail = 0;

    public QueueBasedOnArray(int n) {
        items = new String[n];
        this.n = n;
    }

    public boolean enqueue(String data){
        if (tail == n){
            if (head == 0){
                return false;
            }
            for (int i = head;i < tail;i ++){
                items[i - head] = items[i];
            }
            tail -= head;
            head = 0;
        }
        items[tail] = data;
        tail++;
        return true;
    }
    public String dequeue(){
        if (head == tail) {
            return null;
        }
        String res = items[head];
        head++;
        return  res;
    }
}
