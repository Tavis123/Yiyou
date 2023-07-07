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

    private Integer code;//������
    private String message;//������Ϣ
    private Object data;//���ص���������

    Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //���Ϊ�ɹ�
    public static Result success(Object data) {
        return new Result(ResultCode.SUCCESS, "�ɹ�", data);
    }

    //���Ϊ�ɹ�
    public static Result success(String message) {
        return new Result(ResultCode.SUCCESS, message, null);
    }

    //���Ϊ�ɹ�
    public static Result success(Integer code, String message) {
        return new Result(code, message, null);
    }

    //���Ϊ�ɹ�
    public static Result success(Integer code, String message, Object data) {
        return new Result(code, message, data);
    }

    //���Ϊʧ��
    public static Result error(Integer code, String message) {
        return new Result(code, message, null);
    }

    //��Result����תΪJson�ַ���
    public String ObjectToJson(Result result) {
        return JSONObject.toJSONString(result);
    }

}
