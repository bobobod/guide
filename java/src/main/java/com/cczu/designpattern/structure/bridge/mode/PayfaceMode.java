package com.cczu.designpattern.structure.bridge.mode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2021/11/11
 */
public class PayfaceMode implements IPayMode {
    protected Logger logger = LoggerFactory.getLogger(PayfaceMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("⼈人脸⽀支付，⻛风控校验脸部识别");
        return true;
    }
}

