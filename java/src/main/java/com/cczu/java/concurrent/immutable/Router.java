package com.cczu.java.concurrent.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用不可变模式实现线程安全
 *
 * 使用final关键字修饰所有成员变量，避免其被修改，也可以保证多线程环境下被final关键字修饰的变量所引用的对象的初始化安全，即final修饰的字段在其他线程可见时，必定是初始化完成的。
 *
 * 使用private修饰所有成员变量，可以防止子类及其他地方通过引用直接修改变量值。
 *
 * 禁止提供修改内部状态的公开接口(比如我们前面例子中的setXY方法)
 *
 * 禁止不可变类被外部继承，防止子类改变其定义的方法的行为。
 *
 * 如果类中存在数组或集合，在提供给外部访问之前需要做防御性复制
 *
 */
public class Router {
    /**
     * 短信网关对象，通过volatile修饰保证其他线程的可见性
     */
    private static volatile Router instance = new Router();
    /**
     * 短信服务商信息的map，key代表服务器的优先级
     */
    private final Map<Integer, SmsInfo> smsInfoRouteMap;

    private Router() {
        this.smsInfoRouteMap = loadSmsInfoRouteMap();
    }

    /**
     * 模拟从db中获取数据
     *
     * @return
     */
    private Map<Integer, SmsInfo> loadSmsInfoRouteMap() {
        Map<Integer, SmsInfo> smsInfoMap = new HashMap<>();
        smsInfoMap.put(1, new SmsInfo(111L, "baidu.com", 181L));
        smsInfoMap.put(2, new SmsInfo(222L, "aliyun.com", 182L));
        smsInfoMap.put(3, new SmsInfo(333L, "wangyi.com", 183L));
        return smsInfoMap;
    }

    /**
     * 获取短信服务商
     *
     * @return
     */
    public Map<Integer, SmsInfo> getSmsInfoRouteMap() {
        // 防止对短信路由器进行修改，进行防御性复制
        return Collections.unmodifiableMap(deepCopy(smsInfoRouteMap));
    }

    private Map<Integer, SmsInfo> deepCopy(Map<Integer, SmsInfo> smsInfoRouteMap) {
        Map<Integer, SmsInfo> map = new HashMap<>(smsInfoRouteMap.size());
        for (Map.Entry<Integer, SmsInfo> entry : smsInfoRouteMap.entrySet()) {
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    public static Router getInstance() {
        return instance;
    }

    public static void setInstance(Router instance) {
        Router.instance = instance;
    }

    public void ChangeRouteInfo() {
        // 更新数据库中短信服务商信息
        // updateDb()

        // 更新内存中短信服务的列表
        Router.setInstance(new Router());


    }
}
