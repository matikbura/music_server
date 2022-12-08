package com.lj.music_server.controller;

import com.lj.music_server.enums.HttpStatusEnum;
import com.lj.music_server.exeption.NoneAuthException;
import com.lj.music_server.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 处理空指针的异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =NullPointerException.class)
    public Result<Object> exceptionHandler(HttpServletRequest req, NullPointerException e){
        log.error("发生空指针异常！原因是:",e);
        return new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    /**
     * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
    public Result<Object> exceptionHandler(HttpServletRequest req, Exception e){
        log.error("未知异常！原因是:",e);
        return new Result<>(false, HttpStatusEnum.INTERNAL_SERVER_ERROR, e.getMessage());
    }
    @ExceptionHandler(value = NoneAuthException.class)
    public Result<Object> exceptionHandler(HttpServletRequest req, NoneAuthException e){
        log.error("无权限访问异常:",e);
        return new Result<>(false, HttpStatusEnum.UNAUTHORIZED, e.getMessage());
    }
}