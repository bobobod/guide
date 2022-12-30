package com.cczu.designpattern.behavior.observe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class LotteryServiceImpl extends LotteryService {

    private Logger logger = LoggerFactory.getLogger(LotteryServiceImpl.class);

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    public LotteryResult doDraw(String uId) {
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date());
    }

}
