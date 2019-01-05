package com.clarkrao.springboot.controller;

import com.clarkrao.springboot.dao.MongoDbJpaDao;
import com.clarkrao.springboot.dao.UserRepository;
import com.clarkrao.springboot.entity.AppEntity;
import com.clarkrao.springboot.entity.ResultMap;
import com.clarkrao.springboot.entity.TUser;
import com.clarkrao.springboot.entity.User;
import com.clarkrao.springboot.mapper.UserMapper;
import com.clarkrao.springboot.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/25 21:27
 * @Description:
 */

@RestController
public class HelloWorldController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MongoDbJpaDao mongoDBJPADao;

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

    /**
     * 通过apiKey获取AppEntity接口
     *
     * @param apiKey
     * @return
     */
    @GetMapping("/appEntity/{apiKey}")
    public ResultMap getAppEntityByApiKey(@PathVariable("apiKey") String apiKey) {
        AppEntity byApiKey = mongoDBJPADao.findByApiKey(apiKey);
        ResultMap success = ResultUtil.success(byApiKey);
        return success;
    }

    /**
     * 增加appEntity接口
     *
     * @param appEntity
     * @return
     */
    @PostMapping("/appEntity")
    public ResultMap addAppEntity(@RequestBody AppEntity appEntity) {
        if (appEntity == null) {
            return ResultUtil.error(-1, "entity is null");
        }
        ResultMap resultMap = ResultUtil.success();
        mongoDBJPADao.save(appEntity);
        return resultMap;
    }

    /**
     * 获取所有的AppEntity
     *
     * @return
     */
    @GetMapping("/appEntitis")
    public ResultMap getAllAppEntity() {
        ResultMap resultMap = null;
        List<AppEntity> all = mongoDBJPADao.findAll();
        resultMap = ResultUtil.success(all);
        return resultMap;
    }

    @GetMapping("/tusers")
    public ResultMap getTUser() {
        ResultMap resultMap = new ResultMap();
        List<TUser> all = userMapper.findAll();
        resultMap = ResultUtil.success(all);
        return resultMap;
    }
    @PostMapping("/user")
    public ResultMap addTUser(@RequestBody TUser user){
        userMapper.insert(user);
        return ResultUtil.success();
    }
}
