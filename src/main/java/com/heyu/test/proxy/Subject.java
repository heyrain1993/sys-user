package com.heyu.test.proxy;

import org.springframework.stereotype.Component;

@Component
public class Subject implements Interface{

    public String test() {
        System.out.println("代理中.....");

        return "hello";
    }
}
