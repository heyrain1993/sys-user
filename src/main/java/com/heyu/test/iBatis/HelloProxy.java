package com.heyu.test.iBatis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxy implements InvocationHandler {

    private HelloServiceImpl helloService;

    public HelloProxy(HelloServiceImpl helloService){
        this.helloService = helloService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是JDK动态代理");
        Object result = method.invoke(helloService,args);
        System.out.println("执行 结束");
        return result;
    }
}
