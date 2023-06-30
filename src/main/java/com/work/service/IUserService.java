package com.work.service;

import com.work.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.common.Result;
import jakarta.servlet.http.HttpSession;

public interface IUserService extends IService<User> {
    //登录
    Result login(String username, String password);

    //注册
    Result register(String username, String password);

    //更新用户
    Result update(User user) throws Exception;

//    //判断用户是否登录（实际上就是从session取出用户id去数据库查询并比对）
//    Result isLogin(HttpSession session);

    Result updatePassword(User user);

    //忘记密码
    Result forgetPassword(String phone);
}
