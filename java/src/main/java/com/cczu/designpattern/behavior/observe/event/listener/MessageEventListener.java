package com.cczu.designpattern.behavior.observe.event.listener;

import com.cczu.designpattern.behavior.observe.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class MessageEventListener implements EventListener {
    private Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("给⽤用户 {} 发送短信通知(短信):{}", result.getuId(),
                result.getMsg());
    }
}
