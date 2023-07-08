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
    //登录
    public Result login(String username, String password) {
        //根据用户名去数据库查找用户
        User getUser = userMapper.selectByUsername(username);
        if (getUser == null) {
            return Result.error(ResultCode.ERROR, "不存在该用户!");
        }
        //对比密码（数据库取出用户的密码是加密的，因此要把前端传来的用户密码加密再比对）
        if (!getUser.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            return Result.error(ResultCode.ERROR, "用户名或密码错误!");
        }
        //设置token
        String token = TokenUtil.getToken(username, password);
        getUser.setToken(token);
        //存入数据库
        userMapper.updateById(getUser);
        //设定登录成功消息并返回token
        return Result.success(ResultCode.SUCCESS, "登录成功!", token);
    }

    @Override
    //注册
    public Result register(String username, String password) {
        //根据用户名去数据库中查找是否存在该用户
        User getUser = userMapper.selectByUsername(username);
        if (getUser != null) {
            return Result.error(ResultCode.ERROR, "用户名已存在!");
        } else {
            getUser = new User();
            //设置用户账号
            getUser.setUsername(username);
            //默认初始昵称和用户名相同
            getUser.setNickname(username);
            //加密存储用户的密码
            getUser.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            //存入数据库
            userMapper.insert(getUser);
            return Result.success(ResultCode.SUCCESS, "注册成功!");
        }
    }

    //登出
    @Override
    public Result logout(String token, String username) {
        //根据用户名去数据库查找用户
        User getUser = userMapper.selectByUsername(username);
        if (getUser == null) {
            return Result.error(ResultCode.ERROR, "不存在该用户!");
        }
        //token错误
        if (!getUser.getToken().equals(token)) {
            return Result.error(ResultCode.ERROR, "token错误!");
        }
        //清除token
        getUser.setToken(null);
        //存入数据库
        userMapper.updateById(getUser);
        return Result.success("登出成功!");
    }

    //重置密码
    @Override
    public Result updatePassword(String newPassword, String username) {
        //去数据库查找用户
        User user = userMapper.selectByUsername(username);
        //修改该用户的密码
        user.setPassword(DigestUtils.md5DigestAsHex(newPassword.getBytes()));
        return Result.success(ResultCode.SUCCESS, "修改密码成功!");
    }

    //更换头像
    @Override
    public Result updateAvatar(String url, String username) {
        //去数据库查找用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error(ResultCode.ERROR, "不存在该用户!");
        }
        //修改该用户的头像
        user.setAvatar(url);
        //存入数据库
        userMapper.updateById(user);
        return Result.success(ResultCode.SUCCESS, "修改头像成功!");
    }

    //获取用户信息
    @Override
    public Result getInfo(String username) {
        //去数据库查找用户
        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return Result.error(ResultCode.ERROR, "不存在该用户!");
        }
        return Result.success(ResultCode.SUCCESS, "获取用户信息成功!", user);
    }

    //修改用户信息
    @Override
    public Result updateInfo(User user) {
        //去数据库查找用户
        User getUser = userMapper.selectByUsername(user.getUsername());
        //修改该用户的信息
        try {
            updateObject.objectOverlap(getUser, user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.success(ResultCode.SUCCESS, "修改用户信息成功!");
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

    @Override
    public User selectUserById(String userId)//通过账号来查询某个账户
    {
        return userMapper.selectUserById(userId);
    }

    @Override
    public int updateNum(int cosmmodityNum, String userId)//改变某个账号的发布商品数目；
    {
        return userMapper.updateNum(cosmmodityNum, userId);
    }

    @Override
    public int updateMoney(double money, String userId) {
        return userMapper.updateMoney(money, userId);
    }



}
