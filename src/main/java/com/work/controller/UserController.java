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
        Result result = userService.register(username, password);
        return result;
    }

    //用户登录
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        Result result = userService.login(username, password);
        return result;
    }

    //更换头像
    @PutMapping("/updateavatar")
    public Result updateAvatar(@RequestParam String avatar, @RequestParam String username) {
        Result result = userService.updateAvatar(avatar, username);
        return result;
    }

    //获取用户信息
    @GetMapping("/getinfo")
    public Result getInfo(@RequestParam String username) {
        Result result = userService.getInfo(username);
        return result;
    }

    //修改用户信息
    @PutMapping("/updateinfo")
    public Result updateInfo(@RequestBody User user) {
        Result result = userService.updateInfo(user);
        return result;
    }


//    //编辑用户信息
//    @PutMapping("/update")
//    public Result updateUser(@RequestBody User user) throws Exception {
//        return null;
//    }

    //用户登出
    @GetMapping("/logout")
    public Result<Void> logout(@RequestParam String token, @RequestParam String username) {
        Result result = userService.logout(token, username);
        return result;
    }

    //重置密码
    @PutMapping("/updatepassword")
    public Result updatePassword(@RequestParam String newPassword, @RequestParam String username) {
        Result result = userService.updatePassword(newPassword, username);
        return result;
    }

}
