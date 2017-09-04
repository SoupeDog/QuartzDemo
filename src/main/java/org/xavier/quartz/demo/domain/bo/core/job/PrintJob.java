package org.xavier.quartz.demo.domain.bo.core.job;

import org.apache.commons.lang3.time.FastDateFormat;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.TimeZone;

/**
 * 描述信息：<br/>
 * 控制台输出任务
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public class PrintJob implements Job {
    FastDateFormat fastDateFormat;

    {
        System.out.println("-------------- init FastDateFormat --------------");
        fastDateFormat = FastDateFormat.getInstance("yyyy年MM月dd HH:mm:ss", TimeZone.getDefault());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(fastDateFormat.hashCode());
        JobDataMap jdMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println(jobExecutionContext.getRefireCount());
        System.out.println("任务起始时间：" + fastDateFormat.format(jdMap.get("ts")));
        System.out.println("任务最后更新时间：" + fastDateFormat.format(jdMap.get("lastUpdateTs")));
        System.out.println(jdMap.get("msg"));
        System.out.println("任务完成时间：" + fastDateFormat.format(System.currentTimeMillis()));
    }
}
