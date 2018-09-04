package com.heyu.test.iBatis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test1 {

    public void sayHello(String name){
        System.out.println("Hello " + name);
    }

    public static void main(String[] args){
        try {
            Class<?> clazz = Class.forName(Test1.class.getName());
            Test1 test1 = (Test1) clazz.newInstance();
            Method method = clazz.getMethod("sayHello",String.class);
            method.invoke(test1,"zhangsan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
