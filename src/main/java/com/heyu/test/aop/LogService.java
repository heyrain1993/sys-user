package com.heyu.test.aop;

import org.springframework.stereotype.Service;

@Service
public class LogService {

    public void insert(){
        System.out.println("start insert data to database...");
    }

    public String update(){
        System.out.println("start to update...");
        return "update success";
    }

    public void query(){
        System.out.println("start to query...");
        throw new RuntimeException("query error");
    }
}
