package org.xavier.quartz.demo.config;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述信息：<br/>
 * 自定义 Bean
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
@Configuration
public class CustomerBean {

    @Bean("myScheduler")
    public Scheduler scheduler() throws SchedulerException {
        SchedulerFactory factory = new StdSchedulerFactory();
        return factory.getScheduler();
    }

//    @Bean
//    public RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}
