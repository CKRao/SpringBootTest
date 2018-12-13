package com.clarkrao.springboot.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Author: ClarkRao
 * @Date: 2018/12/13 22:08
 * @Description: 定时任务类
 */
@Component
public class Jobs {
    /**
     * 格式化日期
     */
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT
            = new SimpleDateFormat("YYYY年-MM月-DD日 HH:mm:ss");
    /**
     * 定义常量时间一分钟
     */
    public static final long ONE_MINUTE = 60 * 1000;

    public static final long TEN_SECOND = 10 * 1000;
    /**
     * 定时任务一分钟后执行
     */
    @Scheduled(fixedDelay = TEN_SECOND)
    public void fixedDelayJob() throws InterruptedException {
        Thread.sleep(2*10000);
        System.out.println(SIMPLE_DATE_FORMAT.format(new Date())+" >> fixedDelay执行....");
    }

    /**
     * 定时任务每一分钟执行
     */
    @Scheduled(fixedRate = TEN_SECOND)
    public void fixedRateJob() throws InterruptedException {
        Thread.sleep(2*10000);
        System.out.println(SIMPLE_DATE_FORMAT.format(new Date())+" >> fixedRateJob执行....");
    }

    /**
     * cron表达式
     */
    @Scheduled(cron = "0 20 22 * * ?")
    public void cronJob() {
        System.out.println(SIMPLE_DATE_FORMAT.format(new Date())+" >> cronJob执行....");
    }
}
