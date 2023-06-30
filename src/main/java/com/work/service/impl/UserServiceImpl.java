package com.work.service.impl;

import com.work.common.Constants;
import com.work.controller.UserController;
import com.work.pojo.User;
import com.work.mapper.UserMapper;
import com.work.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.utils.ClassExamine;
import com.work.common.Result;
import com.work.utils.TokenUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
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
        //设定登录成功消息
        return Result.success("200", "登录成功!", getUser);
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

    @Override
    //更新用户
    public Result update(User user) throws Exception {
        Result result = new Result<>();
        //去数据库查找用户
        User getUser = userMapper.selectById(user.getId());
        if (getUser == null) {
            result.setResultFailed("用户不存在!");
            return result;
        }
        //检测传来的对象里面字段值是否为空，若是就用数据库里面的对象相应字段值补上
        if (!StringUtils.hasText(user.getPassword())) {
            //加密储存
            user.setPassword(DigestUtils.md5DigestAsHex(getUser.getPassword().getBytes()));
        }
        //对象互补
        ClassExamine.objectOverlap(getUser, user);
        //存入数据库
        userMapper.update(user, null);
        result.setResultSuccess("修改用户成功!", user);
        return result;
    }

//    @Override
//    public Result<User> isLogin(HttpSession session) {
//        Result<User> result = new Result<>();
//        //从session取出信息
//        User sessionUser = (User) session.getAttribute(UserController.SESSION_NAME);
//        //若session里面没有用户信息，说明用户未登录
//        if (sessionUser == null) {
//            result.setResultFailed("用户未登录!");
//            return result;
//        }
//        //登录了则去数据库取出信息进行比对
//        User getUser = userMapper.selectById(sessionUser.getId());
//        if (getUser == null || !getUser.getPassword().equals(sessionUser.getPassword())) {
//            result.setResultFailed("用户信息无效!");
//            return result;
//        }
//        result.setResultSuccess("用户已登录!", getUser);
//        return result;
//    }

    @Override//未实现好
    public Result updatePassword(User user) {
        Result result = new Result<>();
        //修改该用户的密码
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        return result;
    }

    //忘记密码
    @Override
    public Result forgetPassword(String phone) {

    }
}
