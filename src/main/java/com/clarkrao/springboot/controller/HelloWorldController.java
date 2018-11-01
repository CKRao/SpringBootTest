package com.clarkrao.springboot.controller;

import com.clarkrao.springboot.dao.UserRepository;
import com.clarkrao.springboot.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/25 21:27
 * @Description:
 */

@RestController
public class HelloWorldController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/hello")
    public String index() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/setUser")
    public String setUser() {
        User user = new User("clark", "123456",
                "clarkrao25@gmail.com", "CR", "2018-10-28");
        User user1 = userRepository.save(user);
        if (user != null) {
            return "success";
        }
        return "setUser failed";
    }

    @RequestMapping("/getUser")
    public User getUser() {
        User user = userRepository.findByUserName("clark");
       // System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
       // System.out.println(user.toString());
        return user;
    }

    @RequestMapping("/getCacheUser")
    public String getCacheUser() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println((String) redisTemplate.opsForValue().get("user-key"));
        return (String) redisTemplate.opsForValue().get("user-key");
    }
}
