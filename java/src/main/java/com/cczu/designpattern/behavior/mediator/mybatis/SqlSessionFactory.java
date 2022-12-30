package com.cczu.designpattern.behavior.mediator.mybatis;

/**
 * @author yjz
 * @date 2022/1/29
 */
public interface SqlSessionFactory {
    SqlSession openSqlSession();
}
