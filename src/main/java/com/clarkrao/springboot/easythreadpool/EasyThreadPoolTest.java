package com.clarkrao.springboot.easythreadpool;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/19 22:25
 * @Description: 测试Main方法
 */
public class EasyThreadPoolTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            EasyThreadPool.getInstance().start(() -> {
                try {
                    System.out.println("----------");
                    //休眠100ms
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(EasyThreadPool.getInstance().getThreadCounter());
    }
}
