package org.xavier.quartz.demo.domain.bo.core.mission;

import org.xavier.quartz.demo.domain.bo.core.TimingPlan;

/**
 * 描述信息：<br/>
 * 任务基类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public abstract class BaseMission {
    protected TimingPlan timingPlan;

    public BaseMission() {
    }

    public TimingPlan getTimingPlan() {
        return timingPlan;
    }

    public void setTimingPlan(TimingPlan timingPlan) {
        this.timingPlan = timingPlan;
    }
}
