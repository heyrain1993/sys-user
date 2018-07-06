package com.heyu.test.proxy;

public class MyProxy implements Interface{

    private Subject subject;

    public MyProxy(Subject subject){
        this.subject = subject;
    }

    @Override
    public String test() {
        System.out.println("代理之前......");
        subject.test();
        System.out.println("代理之后......");
        return "";
    }
}
