package com.cczu.designpattern.behavior.observe.event.listener;

import com.cczu.designpattern.behavior.observe.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class MQEventListener implements EventListener {
    private Logger logger = LoggerFactory.getLogger(MQEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("记录⽤用户 {} 摇号结果(MQ):{}", result.getuId(),
                result.getMsg());
    }
}
