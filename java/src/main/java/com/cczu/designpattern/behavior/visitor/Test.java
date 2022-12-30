package com.cczu.designpattern.behavior.visitor;

import com.cczu.designpattern.behavior.visitor.visitor.impl.Parent;
import com.cczu.designpattern.behavior.visitor.visitor.impl.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        DataView dataView = new DataView();
        logger.info("\r\n家⻓视角访问:");
        dataView.show(new Parent());
        logger.info("\r\n校长视角访问:");
        dataView.show(new Principal());
    }
}
