package com.work.service;

import com.work.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
public interface IUserService extends IService<User> {
    //登录
    User login(String username, String password);

    //注册
    boolean register(User user);
}
