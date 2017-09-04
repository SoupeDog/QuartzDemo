package org.xavier.quartz.demo.core.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.FastDateFormat;
import org.quartz.*;
import org.springframework.web.client.RestTemplate;
import org.xavier.quartz.demo.core.factory.RestTemplateFactory;

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
    private FastDateFormat fastDateFormat;
    private RestTemplate restTemplate;

    {
        System.out.println("-------------- init FastDateFormat --------------");
        fastDateFormat = FastDateFormat.getInstance("yyyy年MM月dd HH:mm:ss", TimeZone.getDefault());
        restTemplate = RestTemplateFactory.getInstance();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jdMap = context.getJobDetail().getJobDataMap();
        JobDataMap jdMap2 = context.getTrigger().getJobDataMap();
        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();

//        System.out.println(restTemplate.exchange("http://www.baidu.com", HttpMethod.GET, new HttpEntity<String>(null, new HttpHeaders() {{
//            setContentType(MediaType.APPLICATION_JSON_UTF8);
//        }}), String.class));

        System.out.println("JobName：" + jobDetail.getKey().getName() + "  JobGroup：" + jobDetail.getKey().getGroup());
        System.out.println("TriggerName：" + trigger.getKey().getName() + "  TriggerGroup：" + trigger.getKey().getGroup());// 从结果来看，job 和 trigger 的 name group 是相互独立的
        System.out.println("任务起始时间：" + fastDateFormat.format(jdMap.get("ts")));
        System.out.println("任务最后更新时间：" + fastDateFormat.format(jdMap.get("lastUpdateTs")));
        System.out.println(jdMap.get("msg"));
        System.out.println("任务完成时间：" + fastDateFormat.format(System.currentTimeMillis()));
        try {
            System.out.println(new ObjectMapper().writeValueAsString(jdMap));
            System.out.println(new ObjectMapper().writeValueAsString(jdMap2));// 从结果来看，job 和 trigger 的JobDataMap 是相互独立的
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
