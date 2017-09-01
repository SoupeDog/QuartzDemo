package org.xavier.quartz.demo.domain.bo.core;

/**
 * 描述信息：<br/>
 * 定时任务
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public class TimingPlan {
    private Integer id;
    private String cron;// cron 表达式
    private String description;// 定时任务描述
    private Integer type;// 任务类别
    private Long lastUpdateTs;//最后修改时间
    private Long createTs;//创建时间
    private Boolean active_flag;// 激活状态标识

    public TimingPlan() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getLastUpdateTs() {
        return lastUpdateTs;
    }

    public void setLastUpdateTs(Long lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

    public Long getCreateTs() {
        return createTs;
    }

    public void setCreateTs(Long createTs) {
        this.createTs = createTs;
    }

    public Boolean getActive_flag() {
        return active_flag;
    }

    public void setActive_flag(Boolean active_flag) {
        this.active_flag = active_flag;
    }
}
