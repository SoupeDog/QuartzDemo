package org.xavier.quartz.demo.domain.bo;

/**
 * 描述信息：<br/>
 * 服务错误实体类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public class ErrorResult {

    private Double code;
    private String error;

    public ErrorResult() {
    }

    public ErrorResult(Double code, String msg) {
        this.code = code;
        this.error = msg;
    }

    public Double getCode() {
        return code;
    }

    public void setCode(Double code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
