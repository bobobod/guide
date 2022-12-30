package com.cczu.designpattern.behavior.iterator.lang;

/**
 * @author yjz
 * @date 2022/1/29
 */
public interface Iterable<E> {
    Iterator<E> iterator();
}
