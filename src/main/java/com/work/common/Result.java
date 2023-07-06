package com.work.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author wq
 * @desc ��������
 */
@Setter
@Getter
@NoArgsConstructor
public class Result<T> implements Serializable {

    private String code;//������
    private String message;//������Ϣ
    private T data;//���ص���������

    Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //���Ϊ�ɹ�
    public static Result success(Object data) {
        Result result = new Result(Constants.SUCCESS, "�ɹ�", data);
        return result;
    }

    //���Ϊ�ɹ�
    public static Result success(String message) {
        Result result = new Result(Constants.SUCCESS, message, null);
        return result;
    }

    //���Ϊ�ɹ�
    public static Result success(String code, String message) {
        Result result = new Result(code, message, null);
        return result;
    }

    //���Ϊ�ɹ�
    public static Result success(String code, String message, Object data) {
        Result result = new Result(code, message, data);
        return result;
    }

    //���Ϊʧ��
    public static Result error(String code, String message) {
        Result result = new Result(code, message, null);
        return result;
    }

}
