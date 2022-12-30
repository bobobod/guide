package com.cczu.designpattern.structure.decorator;

/**
 * @author yjz
 * @date 2021/11/14
 */
public class Test {
    /**
     * 在装饰类中有两个􏰀点的地方是;
     * 1)继承了处理理接⼝、
     * 2)提供了构造函数、
     * 3)覆盖了⽅法 preHandle 。
     *
     * @param args
     */
    public static void main(String[] args) {
        LoginSsoDecorator loginSsoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        boolean b = loginSsoDecorator.preHandle("luusqreqwreqwrwqerwqer", "asfd", "t");
        System.out.println("检验结果：" + b);
    }
}
