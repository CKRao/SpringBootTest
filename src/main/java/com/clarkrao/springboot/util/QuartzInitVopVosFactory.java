package com.clarkrao.springboot.util;

import com.clarkrao.springboot.entity.User;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Author: ClarkRao
 * @Date: 2018/12/13 23:08
 * @Description:
 */
@DisallowConcurrentExecution
public class QuartzInitVopVosFactory implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //这里写job代码，就是这个任务，具体要实现什么，就在这里写
        User jobBean = (User) jobExecutionContext.getMergedJobDataMap().get("job");
        //上面这句比较坑,必须用getMergedJobDataMap，不然获取的是一个list<map>对象。不好解析，
        //所有的参数以及其他信息都在JobExecutionContext
        //顺带提一句，如果你没有JobFactory 这个类，在这里是没办法注入任何类的。
        //shift是实体类，
    }


}
