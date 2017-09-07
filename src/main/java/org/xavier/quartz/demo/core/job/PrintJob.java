package org.xavier.quartz.demo.core.job;

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
        System.out.println("-------------- init PrintJob(" + this.hashCode() + ") --------------");
        fastDateFormat = FastDateFormat.getInstance("yyyy年MM月dd HH:mm:ss", TimeZone.getDefault());
        restTemplate = RestTemplateFactory.getInstance();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jdMap = context.getJobDetail().getJobDataMap();

        JobDetail jobDetail = context.getJobDetail();
        Trigger trigger = context.getTrigger();

        //TODO 测试是否能实现 HTTP 回调（结果：能）但 Spring Bean 方式初始化 RestTemplate 对象未成功
//        System.out.println(restTemplate.exchange("http://www.baidu.com", HttpMethod.GET, new HttpEntity<String>(null, new HttpHeaders() {{
//            setContentType(MediaType.APPLICATION_JSON_UTF8);
//        }}), String.class));

        System.out.print("JobName：" + jobDetail.getKey().getName() + "  JobGroup：" + jobDetail.getKey().getGroup());
        System.out.print("  TriggerName：" + trigger.getKey().getName() + "  TriggerGroup：" + trigger.getKey().getGroup());// 从结果来看，job 和 trigger 的 name group 是相互独立的
        System.out.print("  任务起始时间：" + fastDateFormat.format(Long.valueOf((String) jdMap.get("ts"))));
        System.out.print("  任务最后更新时间：" + fastDateFormat.format(Long.valueOf((String) jdMap.get("lastUpdateTs"))));
        System.out.print("  " + jdMap.get("msg"));
        System.out.println("  任务完成时间：" + fastDateFormat.format(System.currentTimeMillis()));
        //TODO 观测 JobDataMap 用
//        try {
//            JobDataMap jdMap2 = context.getTrigger().getJobDataMap();
//            System.out.println(new ObjectMapper().writeValueAsString(jdMap));
//            System.out.println(new ObjectMapper().writeValueAsString(jdMap2));// 从结果来看，job 和 trigger 的JobDataMap 是相互独立的
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }
}
