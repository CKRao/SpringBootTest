package com.clarkrao.springboot.proxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/5 23:57
 * @Description: 自定义拦截器
 */
@Slf4j
public class MyInterceptor implements Interceptor {
    @Override
    public boolean before() {
        log.info("-----------before-----------");
        return true;
    }

    @Override
    public void after() {
        log.info("-----------after-----------");
    }

    @Override
    public Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException {
        log.info("-----------around before-----------");

        Object obj = invocation.proceed();

        log.info("-----------around after-----------");
        return obj;
    }

    @Override
    public void afterReturning() {
        log.info("-----------afterReturning-----------");
    }

    @Override
    public void afterThrowing() {
        log.info("-----------afterThrowing-----------");
    }

    @Override
    public boolean useAround() {
        return true;
    }
}
