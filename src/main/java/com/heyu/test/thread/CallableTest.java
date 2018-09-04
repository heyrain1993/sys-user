package com.heyu.test.thread;

import java.util.concurrent.Callable;

public class CallableTest implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return  "Hello World!";
    }
}
