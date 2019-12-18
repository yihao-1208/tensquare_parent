package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice  //针对所有controller异常处理
public class BaseExceptionHander {

    @ExceptionHandler(value = Exception.class)   //表示只对那个异常进行处理
    @ResponseBody  //返回json数据
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR,e.getMessage());//传入状态码

    }

}
