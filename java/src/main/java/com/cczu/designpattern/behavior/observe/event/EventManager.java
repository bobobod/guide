package com.cczu.designpattern.behavior.observe.event;

import com.cczu.designpattern.behavior.observe.LotteryResult;
import com.cczu.designpattern.behavior.observe.event.listener.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yjz
 * @date 2022/2/5
 */
public class EventManager {
    public enum EventType {
        MQ, Message
    }

    Map<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    /**
     * 订阅
     *
     * @param eventType 事件类型
     * @param listener  监听器
     */
    public void subscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.add(listener);
    }

    /**
     * 取消订阅
     *
     * @param eventType 事件类型
     * @param listener  监听器
     */
    public void unSubscribe(Enum<EventType> eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.remove(listener);
    }

    /**
     * 通知
     *
     * @param eventType 事件类型
     * @param result    结果
     */
    public void notify(Enum<EventType> eventType, LotteryResult result) {
        List<EventListener> eventListeners = listeners.get(eventType);
        for (EventListener eventListener : eventListeners) {
            eventListener.doEvent(result);
        }
    }


}
