package com.work.service;

import com.work.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.common.Result;

public interface UserService extends IService<User> {
    //登录
    Result login(String username, String password);

    //注册
    Result register(String username, String password);

//    //更新用户
//    Result update(User user) throws Exception;

    //重置密码
    Result updatePassword(String newPassword, String username);

    //登出
    Result logout(String token, String username);

    //更换头像
    Result updateAvatar(String url, String username);

    //获取用户信息
    Result getInfo(String username);

    //修改用户信息
    Result updateInfo(User user);
}
