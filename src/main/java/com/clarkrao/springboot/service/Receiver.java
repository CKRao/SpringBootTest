package com.clarkrao.springboot.service;

import com.clarkrao.springboot.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ClarkRao
 * @Date: 2019/1/20 21:27
 * @Description: 消息接受者
 */
@Service
public class Receiver {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("消息接受成功:");
        System.out.println("<-" + message + "->");
    }
}
