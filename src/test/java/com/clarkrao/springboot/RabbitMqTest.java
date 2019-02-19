package com.clarkrao.springboot;

import com.clarkrao.springboot.service.SenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/20 21:31
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

    @Autowired
    public SenderService senderService;

    @Test
    public void send() {
        senderService.send();
    }
}
