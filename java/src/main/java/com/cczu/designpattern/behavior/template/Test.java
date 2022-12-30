package com.cczu.designpattern.behavior.template;

import com.cczu.designpattern.behavior.template.impl.JDNetMall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/2/14
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        NetMall netMall = new JDNetMall("1000001", "*******");
        String base64 = netMall.generateGoodsPoster("https://item.jd.com/100008348542.html");
        logger.info("测试结果:{}", base64);
    }
}
