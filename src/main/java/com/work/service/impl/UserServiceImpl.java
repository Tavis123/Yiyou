package com.work.service.impl;

import com.work.common.ResultCode;
import com.work.pojo.User;
import com.work.mapper.UserMapper;
import com.work.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.common.Result;
import com.work.utils.Identifytool;
import com.work.utils.TokenUtil;
import com.work.utils.updateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    //��¼
    public Result login(String username, String password) {
        //�����û���ȥ���ݿ�����û�
        User getUser = userMapper.selectByUsername(username);
        if (getUser == null) {
            return Result.error(ResultCode.ERROR, "�����ڸ��û�!");
        }
        //�Ա����루���ݿ�ȡ���û��������Ǽ��ܵģ����Ҫ��ǰ�˴������û���������ٱȶԣ�
        if (!getUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return Result.error(ResultCode.ERROR, "�û������������!");
        }
        //����token
        String token = TokenUtil.getToken(username, password);
        getUser.setToken(token);
        //�������ݿ�
        userMapper.updateById(getUser);
        //�趨��¼�ɹ���Ϣ������token
        return Result.success(ResultCode.SUCCESS, "��¼�ɹ�!", token);
    }

    @Override
    //ע��
    public Result register(String username, String password) {
        //�����û���ȥ���ݿ��в����Ƿ���ڸ��û�
        User getUser = userMapper.selectByUsername(username);
        if (getUser != null) {
            return Result.error(ResultCode.ERROR, "�û����Ѵ���!");
        } else {
            getUser = new User();
            //�����û��˺�
            getUser.setUsername(username);
            //Ĭ�ϳ�ʼ�ǳƺ��û�����ͬ
            getUser.setNickname(username);
            //���ܴ洢�û�������
            getUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            //�������ݿ�
            userMapper.insert(getUser);
            return Result.success(ResultCode.SUCCESS, "ע��ɹ�!");
        }
    }

    //�ǳ�
    @Override
    public Result logout(String token, String username) {
        //�����û���ȥ���ݿ�����û�
        User getUser = userMapper.selectByUsername(username);
        if (getUser == null) {
            return Result.error(ResultCode.ERROR, "�����ڸ��û�!");
        }
        //token����
        if (!getUser.getToken().equals(token)) {
            return Result.error(ResultCode.ERROR, "token����!");
        }
        //���token
        getUser.setToken(null);
        //�������ݿ�
        userMapper.updateById(getUser);
        return Result.success("�ǳ��ɹ�!");
    }

    //��������
    @Override
    public Result updatePassword(String newPassword, String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectByUsername(username);
        //�޸ĸ��û�������
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return Result.success(ResultCode.SUCCESS, "�޸�����ɹ�!");
    }

    //����ͷ��
    @Override
    public Result updateAvatar(String url, String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error(ResultCode.ERROR, "�����ڸ��û�!");
        }
        //�޸ĸ��û���ͷ��
        user.setAvatar(url);
        //�������ݿ�
        userMapper.updateById(user);
        return Result.success(ResultCode.SUCCESS, "�޸�ͷ��ɹ�!");
    }

    //��ȡ�û���Ϣ
    @Override
    public Result getInfo(String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error(ResultCode.ERROR, "�����ڸ��û�!");
        }
        return Result.success(ResultCode.SUCCESS, "��ȡ�û���Ϣ�ɹ�!", user);
    }

    //�޸��û���Ϣ
    @Override
    public Result updateInfo(User user) {
        //ȥ���ݿ�����û�
        User getUser = userMapper.selectByUsername(user.getUsername());
        //�޸ĸ��û�����Ϣ
        try {
            updateObject.objectOverlap(getUser, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(ResultCode.SUCCESS, "�޸��û���Ϣ�ɹ�!");
    }

    //ʵ����֤
    @Override
    public Result identify(String realname, String idnumber) {
        //ʹ��IdentifyTool���������ʵ����֤
        Result result = new Result();
        try {
            result = Identifytool.identify(realname, idnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public User selectUserById(String userId)//ͨ���˺�����ѯĳ���˻�
    {
        return userMapper.selectUserById(userId);
    }

    @Override
    public int updateNum(int cosmmodityNum, String userId)//�ı�ĳ���˺ŵķ�����Ʒ��Ŀ��
    {
        return userMapper.updateNum(cosmmodityNum, userId);
    }

    @Override
    public int updateMoney(double money, String userId) {
        return userMapper.updateMoney(money, userId);
    }



}
