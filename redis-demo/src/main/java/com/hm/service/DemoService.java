package com.hm.service;

import com.hm.entity.Address;
import com.hm.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Cacheable(value = "usercache", keyGenerator = "keyGenerator")
    public User findUser(Long id, String firstName, String lastName) {
        System.out.println("无缓存的时候调用这里");
        return new User(id, firstName, lastName);
    }

    @Cacheable(value = "addresscache", keyGenerator = "keyGenerator")
    public Address findAddress(Long id, String province, String city) {
        System.out.println("无缓存的时候调用这里");
        return new Address(id, province, city);
    }
}  