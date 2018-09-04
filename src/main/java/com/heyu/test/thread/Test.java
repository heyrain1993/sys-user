package com.heyu.test.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> futureTask = new FutureTask<>(new CallableTest());

        Thread thread = new Thread(futureTask);
        System.out.println("......");
        thread.start();
        System.out.println(futureTask.get());
    }
}
