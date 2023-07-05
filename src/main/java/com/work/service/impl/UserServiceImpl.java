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
    //登录
    public Result login(String username, String password) {
        //根据用户名去数据库查找用户
        User getUser = userMapper.selectById(username);
        if (getUser == null) {
            return Result.error(Constants.ERROR_400, "不存在该用户!");
        }
        //对比密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return Result.error(Constants.ERROR_400, "用户名或密码错误!");
        }
        //设置token
        String token = TokenUtil.getToken(username, password);
        getUser.setToken(token);
        //设定登录成功消息并返回token
        return Result.success("200", "登录成功!", token);
    }

    @Override
    //注册
    public Result register(String username, String password) {
        //判断用户名是否存在(不可重复)
        User getUser = userMapper.selectById(username);
        if (getUser != null) {
            return Result.error(Constants.ERROR_400, "用户名已存在!");
        } else {
            //设置用户账号密码
            getUser.setUsername(username);
            //加密存储用户的密码
            getUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            //存入数据库
            userMapper.insert(getUser);
            return Result.success("注册成功!");
        }
    }

    //登出
    @Override
    public Result logout(String token, String username) {
        //根据用户名去数据库查找用户
        User getUser = userMapper.selectById(username);
        //对比token
        if (!getUser.getToken().equals(token)) {
            return Result.error(Constants.ERROR_400, "参数错误!");
        }
        //清除token
        getUser.setToken(null);
        //存入数据库
        userMapper.updateById(getUser);
        return Result.success("登出成功!");
    }

//    @Override
//    //更新用户
//    public Result update(User user) throws Exception {
//        Result result = new Result<>();
//        //去数据库查找用户
//        User getUser = userMapper.selectById(user.getId());
//        if (getUser == null) {
//            result.setResultFailed("用户不存在!");
//            return result;
//        }
//        //检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
//        if (!StringUtils.hasText(user.getPassword())) {
//            //加密储存
//            user.setPassword(DigestUtils.md5DigestAsHex(getUser.getPassword().getBytes()));
//        }
//        //对象互补
//        ClassExamine.objectOverlap(getUser, user);
//        //存入数据库
//        userMapper.update(user, null);
//        result.setResultSuccess("修改用户成功!", user);
//        return result;
//    }

    //重置密码
    @Override
    public Result updatePassword(String newPassword, String username) {
        //去数据库查找用户
        User user = userMapper.selectById(username);
        //修改该用户的密码
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return Result.success(Constants.SUCCESS, "修改密码成功!");
    }

    //更换头像
    @Override
    public Result updateAvatar(String url, String username) {
        //去数据库查找用户
        User user = userMapper.selectById(username);
        //修改该用户的头像
        user.setAvatar(url);
        return Result.success(Constants.SUCCESS, "修改头像成功!");
    }

    //获取用户信息
    @Override
    public Result getInfo(String username) {
        //去数据库查找用户
        User user = userMapper.selectById(username);
        return Result.success(Constants.SUCCESS, "获取用户信息成功!", user);
    }

    //修改用户信息
    @Override
    public Result updateInfo(User user) {
        //去数据库查找用户
        User getUser = userMapper.selectById(user.getUsername());
        //修改该用户的信息
        try {
            updateObject.objectOverlap(getUser, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(Constants.SUCCESS, "修改用户信息成功!");
    }

    //实名认证
    @Override
    public Result identify(String realname, String idnumber) {
        //使用IdentifyTool工具类进行实名认证
        Result result = new Result();
        try {
            result = Identifytool.identify(realname, idnumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
