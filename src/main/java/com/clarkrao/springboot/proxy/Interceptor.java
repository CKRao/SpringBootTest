package com.clarkrao.springboot.proxy;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/5 23:49
 * @Description: 拦截器接口
 */
public interface Interceptor {

    /**
     * 前置方法
     * @return
     */
    boolean before();

    /**
     * 后置方法
     */
    void after();

    /**
     * 取代原有事件的方法
     * @param invocation
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    Object around(Invocation invocation) throws InvocationTargetException, IllegalAccessException;

    /**
     * 事后返回方法。
     * 当事件没有发生异常时执行
     */
    void afterReturning();

    /**
     * 事后异常方法。
     * 当事件发生异常时执行
     */
    void afterThrowing();

    /**
     * 是否使用around取代原有方法
     * @return
     */
    boolean useAround();
}
