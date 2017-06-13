package com.hm.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hm on 2017/6/12.
 */
@RestController
public class Test {
    @RequestMapping("/test")
    public String test(){
        return "test success";
    }
}
