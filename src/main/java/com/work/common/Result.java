package com.work.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wq
 * @desc 请求结果类
 */
@Setter
@Getter
@NoArgsConstructor
public class Result<T> implements Serializable {

    private String code;//返回码
    private String message;//返回信息
    private T data;//返回的数据内容

    Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //结果为成功
    public static Result success(Object data) {
        Result result = new Result(Constants.SUCCESS, "成功", data);
        return result;
    }

    //结果为成功
    public static Result success(String message) {
        Result result = new Result(Constants.SUCCESS, message, null);
        return result;
    }

    //结果为成功
    public static Result success(String code, String message) {
        Result result = new Result(code, message, null);
        return result;
    }

    //结果为成功
    public static Result success(String code, String message, Object data) {
        Result result = new Result(code, message, data);
        return result;
    }

    //结果为失败
    public static Result error(String code, String message) {
        Result result = new Result(code, message, null);
        return result;
    }

}
