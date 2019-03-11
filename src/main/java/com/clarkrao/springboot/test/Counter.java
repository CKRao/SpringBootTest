package com.clarkrao.springboot.test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: ClarkRao
 * @Date: 2019/3/11 22:26
 * @Description: CAS TEST
 */
public class Counter {
    /**
     * 原子integer类
     */
    private AtomicInteger atomicI = new AtomicInteger();

    /**
     * 普通int
     */
    private int i = 0;

    public static void main(String[] args) {
        final Counter cas = new Counter();

        ArrayList<Thread> threads = new ArrayList<>(600);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() ->{
                for (int j = 0; j < 10000; j++) {
                    cas.safeCount();
                    cas.count();
                }
            });
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.start();
        }
        //等待所有线程结束
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start
        );
    }

    /**
     * 使用CAS实现线程安全计数器
     */
    private void safeCount() {
        while (true) {
            int i = atomicI.get();
            boolean success = atomicI.compareAndSet(i, ++i);
            if (success) {
                break;
            }
        }
    }

    /**
     * 非线程安全计数器
     */
    private void count() {
        i++;
    }
}
