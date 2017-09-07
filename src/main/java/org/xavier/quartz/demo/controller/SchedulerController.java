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
import org.xavier.quartz.demo.domain.bo.TimingPlan_Cron;
import org.xavier.quartz.demo.domain.bo.TimingPlan_Simp;

import java.util.Date;


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

    @PostMapping("/set/cron")
    public ResponseEntity<?> setJob(@RequestBody TimingPlan_Cron timingPlan) {
        try {
            timingPlan.init();
            JobDetail jobDetail = JobBuilder.newJob().ofType(PrintJob.class)            // 构造 JobDetail
                    .withIdentity(timingPlan.getName_Job(), timingPlan.getGroup_Job())
                    .withDescription(timingPlan.getDescription())
                    .setJobData(new JobDataMap() {{
                        put("ts", System.currentTimeMillis() + "");
                        put("msg", timingPlan.getDescription());
                        put("lastUpdateTs", System.currentTimeMillis() + "");
                    }})
                    .build();

            Trigger trigger = TriggerBuilder            // 构造 Trigger
                    .newTrigger()
                    .withIdentity(timingPlan.getName_Trigger(), timingPlan.getGroup_Trigger())
                    .withSchedule(CronScheduleBuilder.cronSchedule(timingPlan.getCron()))
                    //TODO 观测 JobDataMap 用
//                .usingJobData(new JobDataMap() {{
//                    put("ts2", System.currentTimeMillis() + "");
//                    put("msg2", timingPlan.getDescription());
//                    put("lastUpdateTs2", System.currentTimeMillis() + "");
//                }})
                    .build();

            scheduler.scheduleJob(jobDetail, trigger);
            return success();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return fail(500d, e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

    @PostMapping("/set/simple")
    public ResponseEntity<?> setJob2(@RequestBody TimingPlan_Simp timingPlan) {
        try {
            timingPlan.init();
            JobDetail jobDetail = JobBuilder.newJob().ofType(PrintJob.class)            // 构造 JobDetail
                    .withIdentity(timingPlan.getName_Job(), timingPlan.getGroup_Job())
                    .withDescription(timingPlan.getDescription())
                    .setJobData(new JobDataMap() {{
                        put("ts", System.currentTimeMillis() + "");
                        put("msg", timingPlan.getDescription());
                        put("lastUpdateTs", System.currentTimeMillis() + "");
                    }})
                    .build();

            Trigger trigger;            // 构造 Trigger
            if (timingPlan.getStartTs() == null) {
                trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(timingPlan.getName_Trigger(), timingPlan.getGroup_Trigger())
                        .withSchedule(SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(timingPlan.getInterval())
                                .withRepeatCount(timingPlan.getRepeatTime())
                        )
                        //TODO 观测 JobDataMap 用
//                .usingJobData(new JobDataMap() {{
//                    put("ts2", System.currentTimeMillis() + "");
//                    put("msg2", timingPlan.getDescription());
//                    put("lastUpdateTs2", System.currentTimeMillis() + "");
//                }})
                        .build();
            } else {
                trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity(timingPlan.getName_Trigger(), timingPlan.getGroup_Trigger())
                        .startAt(new Date(timingPlan.getStartTs()))
                        .withSchedule(SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(timingPlan.getInterval())
                                .withRepeatCount(timingPlan.getRepeatTime())
                        )
                        //TODO 观测 JobDataMap 用
//                .usingJobData(new JobDataMap() {{
//                    put("ts2", System.currentTimeMillis() + "");
//                    put("msg2", timingPlan.getDescription());
//                    put("lastUpdateTs2", System.currentTimeMillis() + "");
//                }})
                        .build();
            }

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
            timingPlan.init();
            scheduler.deleteJob(new JobKey(timingPlan.getName_Job(), timingPlan.getGroup_Job()));
            return success();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return fail(500d, e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

}
