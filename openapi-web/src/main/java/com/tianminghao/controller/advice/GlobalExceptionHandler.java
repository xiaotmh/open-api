package com.tianminghao.controller.advice;

import com.tianminghao.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: Athena
 * @date: 2020/9/25 19:00
 * @description: 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result allException(Exception e){

        if(e instanceof RuntimeException) {
            return new Result(false, "数据异常或服务器正忙");
        }else if(e instanceof ClassCastException||e instanceof RuntimeException){
            return new Result(false, "输入格式有误！");
        }else {
            return new Result(false, "服务器开小差啦");
        }
    }





}
