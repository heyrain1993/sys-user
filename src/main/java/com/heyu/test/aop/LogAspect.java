package com.heyu.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogAspect {

    @Pointcut("execution(* com.heyu.test.aop.LogService.*(..))")
    public void pointCut(){};

    @Around("pointCut()")
    public void aroundPoint(ProceedingJoinPoint joinPoint){

        System.out.println("around aop before...");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("around aop after...");
    }

    @Before("pointCut()")
    public void beforePoint(){

        System.out.println("before aop ...");

    }

    @After("pointCut()")
    public void afterPoint(){

        System.out.println("after aop before...");

    }

    @AfterReturning(pointcut = "pointCut()",returning = "returnObj")
    public void afterReturningPoint(JoinPoint joinPoint,Object returnObj){

        System.out.println("afterReturn aop " + returnObj);
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "error")
            public void afterThrowPoint(JoinPoint joinPoint,Throwable error){

        System.out.println("afterThrowing aop " + error);
    }
}
