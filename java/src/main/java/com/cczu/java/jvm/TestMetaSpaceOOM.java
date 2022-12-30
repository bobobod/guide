package com.cczu.java.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestMetaSpaceOOM {
    public static void main(String[] args) {
        // 模拟metaspace溢出
        // jvm参数 -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC  -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Car.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    if (method.getName().equals("run")){
                        System.out.println("启动汽车前准备。。。");
                        return proxy.invokeSuper(obj,args);
                    }
                    return null;
                }
            });
            Car o = (Car) enhancer.create();
            o.run();
        }
    }
    static class Car{
        public void run(){
            System.out.println("汽车启动。。。");
        }
    }
}
