package org.xavier.quartz.demo.core.factory;

import org.springframework.web.client.RestTemplate;

/**
 * 描述信息：<br/>
 * RestTemplate 工厂
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/4
 * @since Jdk 1.8
 */
public class RestTemplateFactory {
    private volatile static RestTemplate restTemplate;

    private RestTemplateFactory() {
    }

    public static RestTemplate getInstance() {
        if (restTemplate == null) {
            synchronized (RestTemplate.class) {
                if (restTemplate == null) {
                    restTemplate = new RestTemplate();
                }
            }
        }
        return restTemplate;
    }

}
