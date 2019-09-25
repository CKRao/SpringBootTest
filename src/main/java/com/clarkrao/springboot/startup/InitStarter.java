package com.clarkrao.springboot.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ClarkRao
 * @Date: 2019/9/25 22:45
 * @Description: 初始化启动类
 */
@Component
@Order(1)
public class InitStarter implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner-1 run ");
    }
}
