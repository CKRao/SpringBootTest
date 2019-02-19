package com.clarkrao.springboot.service;

import com.clarkrao.springboot.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/20 21:23
 * @Description: 消息发送者
 */
@Service
public class SenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 消息发送方法
     */
    public void send() {
        System.out.println("RYT 消息发送...");
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, "你好，RYT");
        System.out.println("RYT 消息发送完毕");
    }
}
