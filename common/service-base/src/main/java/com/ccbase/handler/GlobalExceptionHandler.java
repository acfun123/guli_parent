package com.ccbase.handler;


import com.cc.Result;
import com.ccbase.exception.GuliException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        return Result.error().data("error", e.getMessage());
    }

    @ExceptionHandler(ArithmeticException.class)
    public Result zero(){
        return Result.error().data("error", "除0异常");
    }

    @ExceptionHandler(GuliException.class)
    public Result guliError(GuliException e){
        log.error(e.getMsg());
        return Result.error().data(e.getMsg(), e.getCode());
    }
}
