package com.cczu.designpattern.structure.decorator;

public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}
