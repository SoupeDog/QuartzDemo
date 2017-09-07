package org.xavier.quartz.demo.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xavier.quartz.demo.domain.bo.TimingPlan;
import org.xavier.quartz.demo.core.job.PrintJob;


/**
 * 描述信息：<br/>
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
@RestController
public class SchedulerController extends BaseController {
    @Autowired
    Scheduler scheduler;

    @PostMapping("set")
    public ResponseEntity<?> setJob(@RequestBody TimingPlan timingPlan) {
        timingPlan.setCreateTs(System.currentTimeMillis());
        JobDetail jobDetail = JobBuilder.newJob().ofType(PrintJob.class)
                .withIdentity(timingPlan.getName(), timingPlan.getGroup())
                .withDescription(timingPlan.getDescription())
                .setJobData(new JobDataMap() {{
                    put("ts", System.currentTimeMillis() + "");
                    put("msg", timingPlan.getDescription());
                    put("lastUpdateTs", System.currentTimeMillis() + "");
                }})
                .build();

        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(timingPlan.getName(), timingPlan.getGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(timingPlan.getCron()))
                .usingJobData(new JobDataMap() {{
                    put("ts2", System.currentTimeMillis() + "");
                    put("msg2", timingPlan.getDescription());
                    put("lastUpdateTs2", System.currentTimeMillis() + "");
                }})
                .build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            return success();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return fail(500d, e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

    @PostMapping("del")
    public ResponseEntity<?> delJob(@RequestBody TimingPlan timingPlan) {
        try {
            scheduler.deleteJob(new JobKey(timingPlan.getName(), timingPlan.getGroup()));
            return success();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return fail(500d, e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

}
