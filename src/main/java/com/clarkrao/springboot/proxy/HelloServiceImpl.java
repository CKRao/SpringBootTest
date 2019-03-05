package com.clarkrao.springboot.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/5 23:40
 * @Description: HelloService实现类
 */
@Slf4j
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayHello() {
      log.info("HELLO------------>>>>>>>sayHello()");
    }
}
