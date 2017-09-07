package org.xavier.quartz.demo.domain.bo;

/**
 * 描述信息：<br/>
 * Cron 表达式型 定时任务
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/7
 * @since Jdk 1.8
 */
public class TimingPlan_Cron extends TimingPlan {
    private String cron;

    public TimingPlan_Cron() {
        type = 1;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
