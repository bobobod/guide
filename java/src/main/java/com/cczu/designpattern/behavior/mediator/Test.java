package com.cczu.designpattern.behavior.mediator;

import com.alibaba.fastjson.JSON;
import com.cczu.designpattern.behavior.mediator.mybatis.SqlSession;
import com.cczu.designpattern.behavior.mediator.mybatis.SqlSessionFactory;
import com.cczu.designpattern.behavior.mediator.mybatis.SqlSessionFactoryBuilder;
import com.cczu.designpattern.behavior.mediator.po.User;
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;

/**
 * @author yjz
 * @date 2022/2/4
 */
public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        selectOne();
    }

    private static void selectOne() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSqlSession();
            try {
                User user = session.selectOne("com.cczu.designpattern.behavior.mediator.dao.IUserDao.queryUserInfoById", 1L);
                logger.info("测试结果：{}", JSON.toJSONString(user));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
