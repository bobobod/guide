package com.cczu.designpattern.behavior.mediator.mybatis;

import java.sql.Connection;
import java.util.Map;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class Configuration {
    protected Connection connection;
    protected Map<String, String> dataSource;
    protected Map<String, XNode> mapperElement;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setDataSource(Map<String, String> dataSource) {
        this.dataSource = dataSource;
    }

    public void setMapperElement(Map<String, XNode> mapperElement) {
        this.mapperElement = mapperElement;
    }
}
