package com.heyu.test.iBatis;

import java.lang.reflect.Proxy;

public class TestProxy {

    public static void main(String[] args){
        HelloServiceImpl helloService = new HelloServiceImpl();

        HelloProxy handler = new HelloProxy(helloService);

        HelloService service = (HelloService) Proxy.newProxyInstance(helloService.getClass().getClassLoader(),
                helloService.getClass().getInterfaces(),handler);

        service.sayHello("lisi");
    }
}
