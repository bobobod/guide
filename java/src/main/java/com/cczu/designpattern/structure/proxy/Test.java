package com.cczu.designpattern.structure.proxy;

import com.cczu.designpattern.structure.proxy.service.IUserDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        IUserDao userDao = applicationContext.getBean("userDao", IUserDao.class);
        System.out.println(userDao.queryUserInfo("1"));
    }
}
