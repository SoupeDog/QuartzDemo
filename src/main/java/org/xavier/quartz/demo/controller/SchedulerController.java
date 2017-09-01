package org.xavier.quartz.demo.controller;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xavier.quartz.demo.domain.bo.core.TimingPlan;
import org.xavier.quartz.demo.domain.bo.core.mission.PrintMission;


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
    @Autowired
    ApplicationContext context;

    @PostMapping("set")
    public ResponseEntity<?> setScheduler(@RequestBody TimingPlan timingPlan) {
        PrintMission mission = context.getBean(("PrintMission"), PrintMission.class);
        mission.setTimingPlan(timingPlan);
        JobDetail jobDetail = JobBuilder.newJob().ofType(PrintMission.class)
                .withIdentity("myJob").build();
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("myTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(timingPlan.getCron()))
                .build();
        try {
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
            return success();
        } catch (SchedulerException e) {
            e.printStackTrace();
            return fail(500d, e.getMessage(), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }
}
