package com.cczu.designpattern.behavior.observe;

import com.cczu.designpattern.behavior.observe.event.EventManager;
import com.cczu.designpattern.behavior.observe.event.listener.MQEventListener;
import com.cczu.designpattern.behavior.observe.event.listener.MessageEventListener;

public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    protected abstract LotteryResult doDraw(String uId);

    public LotteryResult draw(String uId) {
        LotteryResult lotteryResult = doDraw(uId);
        // 需要什么通知就给调⽤用什么方法
        eventManager.notify(EventManager.EventType.MQ, lotteryResult);
        eventManager.notify(EventManager.EventType.Message, lotteryResult);
        return lotteryResult;

    }
}
