package com.heyu.test.iBatis;

public class HelloServiceImpl implements HelloService{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
