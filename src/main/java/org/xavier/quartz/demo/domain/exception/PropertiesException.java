package org.xavier.quartz.demo.domain.exception;

/**
 * 描述信息：<br/>
 * 参数有误异常实体
 *
 * @author Xavier
 * @version 1.0
 * @date 2017.09.07.
 * @since Jdk 1.8
 */
public class PropertiesException extends RuntimeException{
    public PropertiesException() {
        super();
    }

    public PropertiesException(String msg) {
        super(msg);
    }

    public PropertiesException(Throwable cause) {
        super(cause);
    }

    public PropertiesException(String message, Throwable cause) {
        super(message, cause);
    }
}
