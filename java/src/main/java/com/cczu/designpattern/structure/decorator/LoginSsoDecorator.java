package com.cczu.designpattern.structure.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yjz
 * @date 2021/11/14
 */
public class LoginSsoDecorator extends SsoDecorator {
    private Logger logger = LoggerFactory.getLogger(LoginSsoDecorator.class);
    private static Map<String, String> authMap = new ConcurrentHashMap<>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = super.preHandle(request, response, handler);
        if (!success) return false;
        String userId = request.substring(8);
        String method = authMap.get(userId);
        logger.info("模拟单点登陆方法拦截：{} {}", userId, method);
        return "queryUserInfo".equals(method);
    }
}
