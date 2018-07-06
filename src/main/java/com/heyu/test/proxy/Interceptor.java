package com.heyu.test.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Interceptor {

    @Pointcut("execution(* com.heyu.test.proxy.Subject.*(..))")
    public void point(){

    }

    @Before("point()")
    public void interptor(){
        System.out.println("before");
    }
}
