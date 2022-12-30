package com.cczu.algorithm.stack;

/**
 * @author jianzhen.yin
 * @date 2020/11/1
 */
public class StackBasedOnArray {
    private String[] items;
    private int count; // 栈的数目
    private int n; // 总长度

    public StackBasedOnArray(int n) {
        items = new String[n];
        count = 0;
        this.n = n;
    }
    public boolean push(String data){
        if (count == n){
            return false;
        }
        items[count++] = data;
        return true;
    }
    public String push(){
        if (count == 0) {
            return null;
        }
        String res = items[count-1];
        count--;
        return res;

    }
}
