package com.work.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
public class Result implements Serializable {

    private Integer code;//返回码
    private String message;//返回信息
    private Object data;//返回的数据内容

    Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //结果为成功
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, "成功", data);
    }

    //结果为成功
    public static Result success(String message) {
        return new Result(ResultCode.SUCCESS, message, null);
    }

    //结果为成功
    public static Result success(Integer code, String message) {
        return new Result(code, message, null);
    }

    //结果为成功
    public static Result success(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    //结果为失败
    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    //将Result对象转为Json字符串
    public String ObjectToJson(Result result) {
        return JSONObject.toJSONString(result);
    }

}
