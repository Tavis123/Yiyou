package com.work.controller;

import com.work.pojo.User;
import com.work.common.Result;
import com.work.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wuqi
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //用户注册
    @PostMapping("/register")
    public Result<User> register(@RequestParam String username, @RequestParam String password) {
        Result result = userService.register(username, password);
        return result;
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        Result result = userService.login(username, password);
        return result;
    }

//    /**
//     * 判断用户是否登录
//     *
//     * @param request 请求对象，从中获取session里面的用户信息以判断用户是否登录
//     * @return 结果对象，已经登录则结果为成功，且数据体为用户信息；否则结果为失败，数据体为空
//     */
//    @GetMapping("/is-Login")
//    public Result<User> isLogin(HttpServletRequest request) {
//        //将session传入到用户服务层
//        return userService.isLogin(request.getSession());
//    }

    //编辑用户信息
    @PutMapping("/update")
    public Result<User> updateUser(@RequestBody User user) throws Exception {
        return null;
    }

//    /**
//     * 用户登出
//     *
//     * @param request 请求，用于操作session
//     * @return 结果对象
//     */
//    @GetMapping("/logout")
//    public Result<Void> logout(HttpServletRequest request) {
//        Result<Void> result = new Result<>();
//        //用户登出就是把session里面的用户信息设为null即可
//        request.getSession().setAttribute(SESSION_NAME, null);
//        result.setResultSuccess("用户退出成功！");
//        return result;
//    }

    //忘记密码（重置密码），要通过手机号来接收验证码
    @PutMapping("/forgetpassword")
    public Result forgetPassword(@RequestParam String phone) {
        Result result = userService.forgetPassword(phone);
        return result;
    }

    //修改用户密码
    @PutMapping("/updatepassword")
    public Result updatePassword(@RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        HttpSession session = request.getSession();
        //检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        User sessionUser = (User) session.getAttribute(SESSION_NAME);
        if (sessionUser.getId() != user.getId().intValue()) {
            result.setResultFailed("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = userService.updatePassword(user);
        //如果修改成功，更新session中的用户信息
        if (result.isSuccess()) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

}
