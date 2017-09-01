package org.xavier.quartz.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.xavier.quartz.demo.domain.bo.ErrorResult;

import java.nio.charset.Charset;

/**
 * 描述信息：<br/>
 * Controller 基类
 *
 * @author Xavier
 * @version 1.0
 * @date 2017/9/1
 * @since Jdk 1.8
 */
public class BaseController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> serviceErrorHandler(Throwable e) {
        e.printStackTrace();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        builder.contentType(mediaType);
        return builder.body(new ErrorResult(500d, e.getMessage()));
    }

    public ResponseEntity<?> success() {
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(mediaType);
        return builder.body(null);
    }

    public ResponseEntity<?> success(Object entity) {
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(HttpStatus.OK);
        builder.contentType(mediaType);
        return builder.body(entity);
    }

    public ResponseEntity<?> fail(Double code, String msg, HttpStatus status) {
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        ResponseEntity.BodyBuilder builder = ResponseEntity.status(status);
        builder.contentType(mediaType);
        return builder.body(new ErrorResult(code, msg));
    }

}
