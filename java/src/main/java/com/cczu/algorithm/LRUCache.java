package com.cczu.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private int capacity;

    public LRUCache(int capacity) {
        /*
         *1. 插入顺序：先添加的在前面，后添加的在后面。修改操作不影响顺序
         *
         *2. 访问顺序：所谓访问指的是get/put操作，对一个键执行get/put操作后，其对应的键值对会移动到链表末尾，
         *
         * 注意：所有最末尾的是最近访问的，起始节点是是最久没有被访问的。
         * true for access-order, false for insertion-order
         */
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    public LRUCache() {
        this(16);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > this.capacity;
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(3);
        cache.put("a", "b");
        cache.put("b", "d");
        cache.put("c", "b");

        cache.get("a");
        cache.get("c");
        cache.put("d", "e");
        System.out.println(cache.get("ab"));
    }
}
