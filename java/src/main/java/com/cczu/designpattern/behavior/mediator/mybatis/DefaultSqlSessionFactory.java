package com.cczu.designpattern.behavior.mediator.mybatis;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSqlSession() {
        return new DefaultSqlSession(configuration.connection, configuration.mapperElement);
    }
}
