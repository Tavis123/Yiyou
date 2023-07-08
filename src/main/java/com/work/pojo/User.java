package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Data//�û���
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)//����jsonʱ����password����
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String contactInfo;

    private String phone;

    private String token;

    private String avatar;

    private String signature;

    private String email;

    private String nickname;

    private String realname;

    private String idnumber;

    private int commodityNum;

    private double money;
}
