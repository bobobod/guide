package com.cczu.designpattern.behavior.observe.event.listener;

import com.cczu.designpattern.behavior.observe.LotteryResult;

public interface EventListener {
    void doEvent(LotteryResult result);
}
