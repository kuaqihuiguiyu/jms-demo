package com.hm.ptp;

import com.hm.Constant;
import com.hm.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 点对点模式 - 调用生产者
 * Created by jason-geng on 5/21/17.
 */
@RestController
@RequestMapping(value = "/ptp")
public class PtpController {

    @Autowired
    private PtpProducer ptpProducer;
    @Autowired
    private CityMapper cityMapper;

    @RequestMapping(value = "/send")
    public Object send() {
        ptpProducer.send();
        return Constant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/convertAndSend")
    public Object convertAndSend() {
        ptpProducer.convertAndSend();
        return Constant.RESPONSE_SUCCESS;
    }

    @RequestMapping(value = "/multiDataSend")
    @Transactional
    public Object multiDataSend() {
        cityMapper.insert("1");
        ptpProducer.send();
//      cityMapper.findByState("CA");
        return cityMapper.count();
    }
}