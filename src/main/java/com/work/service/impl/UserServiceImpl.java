package com.work.service.impl;

import com.work.common.Constants;
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
        User getUser = userMapper.selectById(username);
        if (getUser == null) {
            return Result.error(Constants.ERROR_400, "�����ڸ��û�!");
        }
        //�Ա����루���ݿ�ȡ���û��������Ǽ��ܵģ����Ҫ��ǰ�˴������û���������ٱȶԣ�
        if (!getUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return Result.error(Constants.ERROR_400, "�û������������!");
        }
        //����token
        String token = TokenUtil.getToken(username, password);
        getUser.setToken(token);
        //�趨��¼�ɹ���Ϣ������token
        return Result.success("200", "��¼�ɹ�!", token);
    }

    @Override
    //ע��
    public Result register(String username, String password) {
        //�ж��û����Ƿ����(�����ظ�)
        User getUser = userMapper.selectById(username);
        if (getUser != null) {
            return Result.error(Constants.ERROR_400, "�û����Ѵ���!");
        } else {
            //�����û��˺�����
            getUser.setUsername(username);
            //���ܴ洢�û�������
            getUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            //�������ݿ�
            userMapper.insert(getUser);
            return Result.success("ע��ɹ�!");
        }
    }

    //�ǳ�
    @Override
    public Result logout(String token, String username) {
        //�����û���ȥ���ݿ�����û�
        User getUser = userMapper.selectById(username);
        //�Ա�token
        if (!getUser.getToken().equals(token)) {
            return Result.error(Constants.ERROR_400, "��������!");
        }
        //���token
        getUser.setToken(null);
        //�������ݿ�
        userMapper.updateById(getUser);
        return Result.success("�ǳ��ɹ�!");
    }

//    @Override
//    //�����û�
//    public Result update(User user) throws Exception {
//        Result result = new Result<>();
//        //ȥ���ݿ�����û�
//        User getUser = userMapper.selectById(user.getId());
//        if (getUser == null) {
//            result.setResultFailed("�û�������!");
//            return result;
//        }
//        //��⴫���Ķ��������ֶ�ֵ�Ƿ�Ϊ�գ����Ǿ������ݿ�����Ķ�����Ӧ�ֶ�ֵ����
//        if (!StringUtils.hasText(user.getPassword())) {
//            //���ܴ���
//            user.setPassword(DigestUtils.md5DigestAsHex(getUser.getPassword().getBytes()));
//        }
//        //���󻥲�
//        ClassExamine.objectOverlap(getUser, user);
//        //�������ݿ�
//        userMapper.update(user, null);
//        result.setResultSuccess("�޸��û��ɹ�!", user);
//        return result;
//    }

    //��������
    @Override
    public Result updatePassword(String newPassword, String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectById(username);
        //�޸ĸ��û�������
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return Result.success(Constants.SUCCESS, "�޸�����ɹ�!");
    }

    //����ͷ��
    @Override
    public Result updateAvatar(String url, String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectById(username);
        //�޸ĸ��û���ͷ��
        user.setAvatar(url);
        return Result.success(Constants.SUCCESS, "�޸�ͷ��ɹ�!");
    }

    //��ȡ�û���Ϣ
    @Override
    public Result getInfo(String username) {
        //ȥ���ݿ�����û�
        User user = userMapper.selectById(username);
        return Result.success(Constants.SUCCESS, "��ȡ�û���Ϣ�ɹ�!", user);
    }

    //�޸��û���Ϣ
    @Override
    public Result updateInfo(User user) {
        //ȥ���ݿ�����û�
        User getUser = userMapper.selectById(user.getUsername());
        //�޸ĸ��û�����Ϣ
        try {
            updateObject.objectOverlap(getUser, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(Constants.SUCCESS, "�޸��û���Ϣ�ɹ�!");
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

}
