package org.xavier.quartz.demo.domain.bo;

/**
 * 描述信息：<br/>
 * 简单型 定时任务
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/7
 * @since Jdk 1.8
 */
public class TimingPlan_Simp extends TimingPlan {
    private Integer repeatTime;// 重复次数
    private Integer interval;// 间隔触发时间
    private Long startTs;// 任务开始时间毫秒时间戳

    public TimingPlan_Simp() {
        type = 2;
    }

    public Integer getRepeatTime() {
        return repeatTime;
    }

    public void setRepeatTime(Integer repeatTime) {
        this.repeatTime = repeatTime;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Long getStartTs() {
        return startTs;
    }

    public void setStartTs(Long startTs) {
        this.startTs = startTs;
    }
}
