package com.cczu.java.concurrent.immutable;

/**
 * final修饰符
 * Q1:final修饰基本类型域
 * A：被final修饰的实例域，构建对象时必须初始化这样的实例域，也就是说，必须确保在每一个构造器执行之后，这个域的值被设置，并且在后面的操作中，不能够再对它进行修改。下面代码中的E变量就是被final修饰的而必须初始化的实例。
 * 除了实例类型，final还被用来修饰局部变量和静态变量。关于final修饰static变量，放到static中讲解。
 * 放到static中详解。
 * 修饰static变量即静态变量时，此时这样的变量又被称为静态常量，静态常量是属于类的，而不是属于对象，即可以直接利用类名访问静态常量，它被所有对象共有，即1000个对象可以有1000个S（下面代码中的字符串）拷贝，但是只会有1个C，即使没有声明任何对象，C也是存在的。比如Math类中的Math.PI。
 * <p>
 * Q2:final修饰方法
 * A：被final修饰的方法不能够被继承。如下，当我们要改写f2函数的时候会报错。final修饰方法的一个好处是可以提高运行效率，编译器遇到final方法会转入内嵌机制
 * <p>
 * Q3:final修饰类
 * final修饰的类是不可以被继承的。比如常见的string类。当给类加上final修饰后，类内的方法加不加final都是不可以被重写的。
 * <p>
 * Q4:final修饰参数
 * final修饰传入方法的参数时，该参数只读，不可以修改。
 * <p>
 * Q5:final修饰对象
 * final修饰对象时，不可以再指向其它的对象，但是对象的内容是可以修改的。
 */
public final class SmsInfo {
    /**
     * 服务提供方的id
     */
    private final Long id;
    /**
     * 服务提供方的url
     */
    private final String url;
    /**
     * 传输最大字节
     */
    private final Long maxSizeInBytes;

    public SmsInfo(Long id, String url, Long maxSizeInBytes) {
        this.id = id;
        this.url = url;
        this.maxSizeInBytes = maxSizeInBytes;
    }

    @Override
    public String toString() {
        return "SmsInfo{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", maxSizeInBytes=" + maxSizeInBytes +
                '}';
    }

}
