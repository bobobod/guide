package com.cczu.designpattern.behavior.command.service.impl;

import com.cczu.designpattern.behavior.command.service.ICook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/1/29
 */
public class GuangDongCook implements ICook {
    private Logger logger = LoggerFactory.getLogger(ICook.class);
    @Override
    public void doCooking() {
        logger.info("广东厨师烹饪中...");
    }
}
