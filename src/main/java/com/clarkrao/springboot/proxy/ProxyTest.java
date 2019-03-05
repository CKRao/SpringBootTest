package com.clarkrao.springboot.proxy;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/6 0:13
 * @Description:
 */
public class ProxyTest {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        //按照约定获取proxy
        HelloService proxyBean = (HelloService) ProxyBean.getProxyBean(helloService, new MyInterceptor());
        proxyBean.sayHello();
    }
}
