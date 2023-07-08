package com.work.service;

import com.work.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.common.Result;

public interface UserService extends IService<User> {
    //��¼
    Result login(String username, String password);

    //ע��
    Result register(String username, String password);

//    //�����û�
//    Result update(User user) throws Exception;

    //��������
    Result updatePassword(String newPassword, String username);

    //�ǳ�
    Result logout(String token, String username);

    //����ͷ��
    Result updateAvatar(String url, String username);

    //��ȡ�û���Ϣ
    Result getInfo(String username);

    //�޸��û���Ϣ
    Result updateInfo(User user);

    //ʵ����֤
    Result identify(String realname, String idcard);

    //ͨ���˺�����ѯĳ���˻�
    User selectUserById(String userId);

    //�ı�ĳ���˺ŵķ�����Ʒ��Ŀ
    int updateNum(int commodityNum, String userId);

    //�ı�ĳ���˺ŵ����
    int updateMoney(double money, String userId);

}
