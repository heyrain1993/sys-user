package com.heyu.test.redis;

import redis.clients.jedis.Jedis;

public class Producer extends Thread{

    private Jedis jedis = new Jedis("127.0.0.1",6379);

    private int index = 0;

    @Override
    public void run(){

        while (true){
            for (int i = 0; i< 100; i++){
                jedis.lpush("carrierNo","value_" + index++);
            }
            /*try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
    }
}
