package com.cczu.designpattern.structure.bridge.channel;

import com.cczu.designpattern.structure.bridge.mode.IPayMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;


/**
 * @author yjz
 * @date 2021/11/11
 */
public abstract class Pay {
    protected Logger logger = LoggerFactory.getLogger(Pay.class);
    protected IPayMode payMode;

    public Pay(IPayMode mode) {
        this.payMode = mode;
    }

    public abstract String transfer(String uid, String tradeId, BigDecimal amount);
}
