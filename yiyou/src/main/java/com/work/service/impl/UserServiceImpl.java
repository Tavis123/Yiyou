package com.work.service.impl;

import com.work.entity.User;
import com.work.mapper.UserMapper;
import com.work.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;

    //登录
    @Override
    public User login(String username, String password) {
        return baseMapper.selectByUsername(username);
    }

    //注册
    @Override
    public boolean register(User user) {
        return baseMapper.insert(user) > 0;//用户名已存在时无法注册
    }
}
