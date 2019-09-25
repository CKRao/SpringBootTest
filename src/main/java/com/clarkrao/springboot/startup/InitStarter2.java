package com.clarkrao.springboot.startup;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ClarkRao
 * @Date: 2019/9/25 22:47
 * @Description:
 */
@Component
@Order(2)
public class InitStarter2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner-2 run ");
    }
}
