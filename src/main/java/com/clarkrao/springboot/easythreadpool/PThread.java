package com.clarkrao.springboot.easythreadpool;

/**
 * @Author: ClarkRao
 * @Date: 2018/11/19 22:03
 * @Description: 永不退出的线程
 */
public class PThread extends Thread{
    /**
     * 线程池
     */
    private EasyThreadPool pool;

    /**
     * 任务
     */
    private Runnable target;

    /**
     * 是否关闭
     */
    private boolean isShutDown = false;

    /**
     * 是否闲置
     */
    private boolean isIdle = false;

    /**
     * 构造函数
     * @param target
     * @param name
     * @param pool
     */
    public PThread(Runnable target, String name, EasyThreadPool pool) {
        super(name);
        this.target = target;
        this.pool = pool;
    }

    public Runnable getTarget() {
        return target;
    }

    public boolean isIdle() {
        return isIdle;
    }

    @Override
    public void run() {
        //只要没有关闭，则一直不结束该线程
        while (!isShutDown) {
            isIdle = false;
            if (target != null) {
                //运行任务
                target.run();
            }
            try {
                //任务结束了,到闲置状态
                isIdle = true;
                pool.repool(this);
                synchronized (this) {
                    //线程空闲，等待新的任务到来
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isIdle = false;
        }
    }

    /**
     * 关闭线程
     */
    public synchronized  void shutDown() {
        isShutDown = true;
        notifyAll();
    }

    public synchronized void setTarget(Runnable newTarget) {
        this.target = newTarget;
        //设置了任务之后，通知run方法，开始执行这个任务
        notifyAll();
    }


}
