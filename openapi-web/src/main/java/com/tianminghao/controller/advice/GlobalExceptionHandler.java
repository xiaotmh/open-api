package com.tianminghao.controller.advice;

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
    public ModelAndView selectException(Exception e){
        ModelAndView modelAndView = new ModelAndView();
        if(e instanceof RuntimeException) {
            modelAndView.setViewName("error");
            modelAndView.addObject("errorMsg", "服务器正忙，请稍后再试");
        }
        return modelAndView;
    }



}
