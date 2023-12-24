package com.cc;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {
    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    private Result(){}

    public static Result ok(){
        Result res = new Result();
        res.setSuccess(true);
        res.setCode(ResultCode.success);
        res.setMessage("成功");
        return res;
    }

    public static Result error(){
        Result res = new Result();
        res.setSuccess(false);
        res.setCode(ResultCode.error);
        res.setMessage("失败");
        return res;
    }

    public Result data(Map<String, Object> data){
        this.setData(data);
        return this;
    }

    public Result data(String key, Object value){
        data.put(key, value);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }
}
