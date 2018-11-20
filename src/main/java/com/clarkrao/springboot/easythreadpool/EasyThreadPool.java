package com.clarkrao.springboot.easythreadpool;

import java.util.List;
import java.util.Vector;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/19 22:00
 * @Description: 自定义线程池
 */
public class EasyThreadPool {
    private static EasyThreadPool instance = null;

    /**
     * 空闲的线程队列
     */
    private List<PThread> idleThreads;
    /**
     * 已有的线程总数
     */
    private int threadCounter;

    private boolean isShutDown = false;

    private EasyThreadPool() {
        this.idleThreads = new Vector<>(5);
        threadCounter = 0;
    }

    public int getThreadCounter() {
        return threadCounter;
    }

    /**
     * 取得线程池的实例
     *
     * @return
     */
    public synchronized static EasyThreadPool getInstance() {
        if (instance == null) {
            instance = new EasyThreadPool();
        }
        return instance;
    }

    /**
     * 将线程放入线程池中
     *
     * @param repoolingThread
     */
    protected synchronized void repool(PThread repoolingThread) {
        if (!isShutDown) {
            idleThreads.add(repoolingThread);
        } else {
            repoolingThread.shutDown();
        }
    }

    /**
     * 停止池中所有线程
     */
    public synchronized void shutDown() {
        isShutDown = true;
        for (int threadIndex = 0; threadIndex < idleThreads.size(); threadIndex++) {
            PThread pThread = idleThreads.get(threadIndex);
            pThread.shutDown();
        }
    }

    public synchronized void start(Runnable target) {
        PThread thread = null;
        //如果有空闲线程，则直接使用
        if (idleThreads.size() > 0) {
            int lastIndex = idleThreads.size() - 1;
            thread = idleThreads.get(lastIndex);
            idleThreads.remove(thread);
            //立即执行这个任务
            thread.setTarget(target);
        } else {
            //没有空闲线程，则创建线程
            threadCounter++;
            //创建新线程
            thread = new PThread(target, "PThread #" + threadCounter, this);
            //启动这个线程
            thread.start();
        }
    }

}
