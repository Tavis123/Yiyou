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

    //实名认证
    Result identify(String realname, String idcard);

    //通过账号来查询某个账户
    User selectUserById(String userId);

    //改变某个账号的发布商品数目
    int updateNum(int commodityNum, String userId);

    //改变某个账号的余额
    int updateMoney(double money, String userId);

}
