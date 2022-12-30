package com.cczu.designpattern.behavior.iterator.lang;

/**
 * 1. Collection，集合⽅方法部分⽤用于对⾃自定义的数据结构添加通⽤用⽅方法;add、remove、iterator 等核⼼心⽅方法。
 * 2. Iterable，提供获取迭代器器，这个接⼝口类会被Collection继承。
 * 3. Iterator，提供了了两个⽅方法的定义;hasNext、next，会在具体的数据结构中写实现⽅方式。
 *
 * @author yjz
 */
public interface Collection<E, L> extends Iterable<E> {
    boolean add(E e);

    boolean remove(E e);

    boolean addLink(String key, L l);

    boolean removeLink(String key);

}
