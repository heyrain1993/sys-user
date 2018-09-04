package com.heyu.test.redis;

import redis.clients.jedis.Jedis;

import java.util.List;

public class Consumer extends Thread{

    private Jedis jedis = new Jedis("127.0.0.1",6379);

    @Override
    public void run(){
        while (true){
            List<String> strs = jedis.brpop(0,"carrierNo");
            System.out.println(Thread.currentThread().getName() + " " +strs.get(1));
        }
    }
}
