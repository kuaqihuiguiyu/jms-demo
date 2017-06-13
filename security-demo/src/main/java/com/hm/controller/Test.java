package com.hm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hm on 2017/6/13.
 */
@RestController
public class Test {
    @RequestMapping("/test")
    public Map<String, String> test() {
        // 返回map会变成JSON key value方式
        Map<String, String> map = new HashMap<String, String>();
        map.put("content", "hello freewolf~");
        return map;
    }
}
