package com.cczu.designpattern.structure.bridge.mode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2021/11/11
 */
public class PayFingerPrintMode implements IPayMode {
    protected Logger logger = LoggerFactory.getLogger(PayFingerPrintMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("指纹⽀支付，⻛风控校验指纹信息");
        return true;
    }
}

