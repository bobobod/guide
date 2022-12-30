package com.cczu.designpattern.structure.decorator;

/**
 * @author yjz
 * @date 2021/11/14
 */
public abstract class SsoDecorator implements HandlerInterceptor {
    private HandlerInterceptor handlerInterceptor;

    private SsoDecorator() {
    }

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        return handlerInterceptor.preHandle(request,response,handler);
    }
}
