package org.xavier.quartz.demo.domain.bo;

import org.xavier.quartz.demo.domain.exception.PropertiesException;

/**
 * 描述信息：<br/>
 * 定时任务基类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public class TimingPlan {
    protected Integer id;
    protected String group_Job;//任务组
    protected String name_Job;// 任务名称
    protected String group_Trigger;//触发器组
    protected String name_Trigger;// 触发器名称
    protected String description;// 定时任务描述
    protected Integer type;// 任务类别
    protected Long ts;// 请求方上传时间戳
    protected Long lastUpdateTs;//最后修改时间
    protected Long createTs;//创建时间
    protected Boolean active_flag;// 激活状态标识

    public TimingPlan() {
    }

    public void init() {
        //TODO 校验以及初始化部分默认值
        if (group_Job == null || name_Job == null || type == null || type < 0) {
            throw new PropertiesException("必填参数 [name_Job,group_Job,type] 有误。");
        }
        Long currentTs = System.currentTimeMillis();
        this.createTs = currentTs;
        this.lastUpdateTs = currentTs;
        if (this.name_Trigger == null) {
            this.name_Trigger = "T_" + name_Job;
        }
        if (this.group_Trigger == null) {
            this.group_Trigger = "T_" + group_Job;
        }
        this.active_flag = true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroup_Job() {
        return group_Job;
    }

    public void setGroup_Job(String group_Job) {
        this.group_Job = group_Job;
    }

    public String getName_Job() {
        return name_Job;
    }

    public void setName_Job(String name_Job) {
        this.name_Job = name_Job;
    }

    public String getGroup_Trigger() {
        return group_Trigger;
    }

    public void setGroup_Trigger(String group_Trigger) {
        this.group_Trigger = group_Trigger;
    }

    public String getName_Trigger() {
        return name_Trigger;
    }

    public void setName_Trigger(String name_Trigger) {
        this.name_Trigger = name_Trigger;
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

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
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
