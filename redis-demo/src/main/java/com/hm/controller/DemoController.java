package com.hm.controller;

import com.hm.entity.Address;
import com.hm.entity.User;
import com.hm.service.DemoService;
import com.hm.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    @RequestMapping("/test")
    @ResponseBody
    public String putCache() {
        demoService.findUser(1l, "wang", "yunfei");
        demoService.findAddress(1l, "anhui", "hefei");
        System.out.println("这里调用,进行缓存");
        return "ok";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String testCache() {
        User user = demoService.findUser(1l, "wang", "yunfei");
        Address address = demoService.findAddress(1l, "anhui", "hefei");
        System.out.println("这里从缓存中取数据");
        System.out.println("user:" + "/" + user.getFirstName() + "/" + user.getLastName());
        System.out.println("address:" + "/" + address.getProvince() + "/" + address.getCity());
        return "ok";
    }

    @Autowired
    RedisService redisService;

    @RequestMapping("/add")
    @ResponseBody
    public String addKeyValue(String key, String value) {
        System.out.println(key);
        redisService.add(key, value);
        return "add";
    }

    @RequestMapping("/del")
    @ResponseBody
    public String delKeyValue(String key) {
        System.out.println(key);
        redisService.del(key);
        return "del";
    }
}  