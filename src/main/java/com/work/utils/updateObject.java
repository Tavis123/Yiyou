package com.work.utils;

import com.work.pojo.User;

//用于更新用户信息，将前端传来的字段值覆盖数据库中原本的值
public class updateObject {
    //用newInfo的值覆盖oldInfo的值
    public static void objectOverlap(User oldInfo, User newInfo) throws Exception {
        oldInfo.setUsername(newInfo.getUsername());
        oldInfo.setPassword(newInfo.getPassword());
        oldInfo.setAvatar(newInfo.getAvatar());
        oldInfo.setToken(newInfo.getToken());
        oldInfo.setNickname(newInfo.getNickname());
    }

}
