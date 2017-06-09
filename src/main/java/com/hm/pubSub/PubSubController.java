package com.hm.pubSub;

import com.hm.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发布/订阅
 * Created by jason-geng on 5/21/17.
 */
@RestController
@RequestMapping(value = "/pubSub")
public class PubSubController {

    @Autowired
    private PubSubProducer pubSubProducer;

    @RequestMapping(value = "/send")
    public String send() {
        pubSubProducer.send();
        return Constant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/convertAndSend")
    public String convertAndSend() {
        pubSubProducer.convertAndSend();
        return Constant.RESPONSE_SUCCESS;
    }
}
