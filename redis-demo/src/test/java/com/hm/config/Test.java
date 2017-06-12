package com.hm.config;

import redis.clients.jedis.Jedis;

/**
 * Created by hm on 2017/6/9.
 */
public class Test {
    @org.junit.Test
    public void test(){
        Jedis jedis = new Jedis("139.199.15.166",6379);
        jedis.auth("350710135");
        System.out.println("Connection to server sucessfully");
        System.out.println(jedis.ping());
        //查看服务是否运行
        jedis.set("mxb","123");
        System.out.println(jedis.get("mxb"));
//        String url = "/admin/index.html";
//        String roleString = url.substring(1,url.indexOf("/",1));
//        System.out.println(roleString);
    }
}
