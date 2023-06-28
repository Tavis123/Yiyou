package com.work.service;

import com.work.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.common.Result;
import jakarta.servlet.http.HttpSession;

public interface IUserService extends IService<User> {
    //登录
    Result<User> login(User user);

    //注册
    Result<User> register(User user);

    //更新用户
    Result<User> update(User user) throws Exception;

    //判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
    Result<User> isLogin(HttpSession session);

}
