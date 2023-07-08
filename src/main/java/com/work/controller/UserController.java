package com.work.controller;

import com.work.common.Result;
import com.work.pojo.User;
import com.work.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    //用户注册
    @PostMapping("/register")
    public Result register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    //更换头像
    @PutMapping("/updateavatar")
    public Result updateAvatar(@RequestParam String url, @RequestParam String username) {
        return userService.updateAvatar(url, username);
    }

    //获取用户信息
    @GetMapping("/getinfo")
    public Result getInfo(@RequestParam String username) {
        return userService.getInfo(username);
    }

    //修改用户信息
    @PutMapping("/updateinfo")
    public Result updateInfo(@RequestBody User user) {
        return userService.updateInfo(user);
    }

    //用户登出
    @GetMapping("/logout")
    public Result logout(@RequestParam String token, @RequestParam String username) {
        return userService.logout(token, username);
    }

    //重置密码
    @PutMapping("/updatepassword")
    public Result updatePassword(@RequestParam String newPassword, @RequestParam String username) {
        return userService.updatePassword(newPassword, username);
    }

    //实名认证
    @GetMapping("/identify")
    public Result identify(@RequestParam String realname, @RequestParam String idnumber) {
        return userService.identify(realname, idnumber);
    }

}
