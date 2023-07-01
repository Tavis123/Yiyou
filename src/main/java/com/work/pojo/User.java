package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Data//用户表
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)//返回json时忽略password属性
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @Size(min = 6, max = 10, message = "密码长度必须在6-10之间")
    private String password;

    private String contactInfo;

    private String phone;

    private String token;

    private String avatar;

    private String signature;

    private String email;

    private String nickname;
}
