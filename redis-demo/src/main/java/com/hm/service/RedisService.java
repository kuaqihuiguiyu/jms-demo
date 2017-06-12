package com.hm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hm on 2017/6/12.
 */
@Service
public class RedisService {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void add(final String key, final String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void del(final String key) {
        stringRedisTemplate.delete(key);
    }
}
