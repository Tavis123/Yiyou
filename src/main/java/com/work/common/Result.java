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
    private String message;
    private boolean success;//请求是否成功
    private T data;//返回的数据内容

    //设定结果为成功
    public void setResultSuccess(String message) {
        this.message = message;
        this.success = true;
        this.data = null;
    }

    //设定结果为成功
    public void setResultSuccess(String message, T data) {
        this.message = message;
        this.success = true;
        this.data = data;
    }

    //设定结果为失败
    public void setResultFailed(String message) {
        this.message = message;
        this.success = false;
        this.data = null;
    }

}
