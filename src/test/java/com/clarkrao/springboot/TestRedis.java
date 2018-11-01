package com.clarkrao.springboot;

import com.clarkrao.springboot.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: ClarkRao
 * @Date: 2018/10/28 15:56
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("aaa","111");
        Assert.assertEquals("111",stringRedisTemplate.opsForValue().get("aaa"));
    }

    @Test
    public void testObj() throws InterruptedException {
        User user = new User("clark","123456",
                "clarkrao25@gmail.com","CR","2018-10-28");
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
//        operations.set("com.clark",user);
//        operations.set("com.clark.ck",user,1,TimeUnit.SECONDS);
//        Thread.sleep(1000);
//        boolean exits = redisTemplate.hasKey("com.clark.ck");
//        if (exits){
//            System.out.println("exists is true");
//        }else{
//            System.out.println("exists is false");
//        }
        System.out.println(redisTemplate.opsForValue().get("user-key::com.clarkrao.springboot.controller.HelloWorldControllergetUser"));
        Assert.assertEquals("clark",operations.get("com.clark").getUserName());
    }


}
