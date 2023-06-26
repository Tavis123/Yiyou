package com.work.controller;

import com.work.entity.User;
import com.work.service.IUserService;
import com.work.common.Result;
import com.work.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuqi
 */
@RestController
public class UserController {
    //session的key
    public static final String SESSION_NAME = "userInfo";
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册
     *
     * @param user   传入注册用户信息
     * @param errors Validation的校验错误存放对象
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<User> register(@RequestBody @Valid User user, BindingResult errors) {
        Result<User> result;
        //如果校验有错，返回注册失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        //调用service层的注册方法
        result = userService.register(user);
        return result;
    }

    /**
     * 用户登录
     *
     * @param user    传入登录用户信息
     * @param errors  Validation的校验错误存放对象
     * @param request 请求对象，用于操作session
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<User> login(@RequestBody @Valid User user, BindingResult errors, HttpServletRequest request) {
        Result<User> result;
        //如果校验有错，返回登录失败以及错误信息
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setResultFailed(errors.getFieldError().getDefaultMessage());
            return result;
        }
        //调用service层的登录方法
        result = userService.login(user);
        //如果登录成功，把用户信息存入session
        if (result.isSuccess()) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 判断用户是否登录
     *
     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
     */
    @GetMapping("/is-Login")
    public Result<User> isLogin(HttpServletRequest request) {
        //将session传入到用户服务层
        return userService.isLogin(request.getSession());
    }

    /**
     * 用户信息修改
     *
     * @param user    修改后用户信息对象
     * @param request 请求对象，用于操作session
     * @return 修改结果
     */
    @PutMapping("/update")
    public Result<User> update(@RequestBody User user, HttpServletRequest request) throws Exception {
        Result<User> result = new Result<>();
        HttpSession session = request.getSession();
        //检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        User sessionUser = (User) session.getAttribute(SESSION_NAME);
        if (sessionUser.getId() != user.getId().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.update(user);
        //如果修改成功，更新session中的用户信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    /**
     * 用户登出
     *
     * @param request 请求，用于操作session
     * @return 结果对象
     */
    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        //用户登出就是把session里面的用户信息设为null即可
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setResultSuccess("用户退出成功！");
        return result;
    }

}
