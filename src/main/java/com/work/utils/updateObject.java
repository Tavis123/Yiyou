package com.work.utils;

import com.work.pojo.User;

//���ڸ����û���Ϣ����ǰ�˴������ֶ�ֵ�������ݿ���ԭ����ֵ
public class updateObject {
    //��newInfo��ֵ����oldInfo��ֵ
    public static void objectOverlap(User oldInfo, User newInfo) throws Exception {
        oldInfo.setUsername(newInfo.getUsername());
        oldInfo.setPassword(newInfo.getPassword());
        oldInfo.setAvatar(newInfo.getAvatar());
        oldInfo.setToken(newInfo.getToken());
        oldInfo.setNickname(newInfo.getNickname());
    }

}
