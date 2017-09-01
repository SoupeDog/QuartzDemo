package org.xavier.quartz.demo.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * 描述信息：<br/>
 * 时间工具类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
@Component
public class TimeHelper {
    static SimpleDateFormat type_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat type_2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
    static SimpleDateFormat type_3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat type_4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    static SimpleDateFormat type_5 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String fromType_1(Long ts) {
        return type_1.format(ts);
    }

    public static String fromType_2(Long ts) {
        return type_2.format(ts);
    }

    public static void main(String[] args) {
        System.out.println(TimeHelper.fromType_2(System.currentTimeMillis()));
    }
}
