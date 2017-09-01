package org.xavier.quartz.demo.domain.bo.core.mission;

import org.apache.commons.lang3.time.FastDateFormat;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 描述信息：<br/>
 * 控制台输出任务
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
@Scope("prototype")
@Component("PrintMission")
public class PrintMission extends BaseMission implements Job {
    private String msg = "什么鬼?不让设置？";
    @Autowired
    FastDateFormat fastDateFormat;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("任务起始时间：" + fastDateFormat.format(timingPlan.getCreateTs()));
        System.out.println(msg);
        System.out.println("任务完成时间：" + fastDateFormat.format(System.currentTimeMillis()));
    }
}
