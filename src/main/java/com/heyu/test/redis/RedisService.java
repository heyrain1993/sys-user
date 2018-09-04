package com.heyu.test.redis;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import redis.clients.jedis.Jedis;

public class RedisService {

    public static void main(String[]  args){
        Producer producer = new Producer();
        producer.start();
        for(int i = 0; i <10; i++){
            Consumer consumer = new Consumer();
            consumer.start();
        }
    }


}
