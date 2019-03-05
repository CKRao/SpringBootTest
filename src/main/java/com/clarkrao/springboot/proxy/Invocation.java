package com.clarkrao.springboot.proxy;

import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/5 23:52
 * @Description:
 */

public class Invocation {
    /**
     * 参数对象数组
     */
    private Object[] params;

    /**
     * 方法
     */
    private Method method;

    /**
     * 目标对象
     */
    private Object target;


    public Invocation(Object target, Method method, Object[] params) {
        this.params = params;
        this.target = target;
        this.method = method;
    }

    /**
     * 反射方法
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public Object proceed() throws IllegalAccessException,InvocationTargetException {
        return  method.invoke(target , params);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
