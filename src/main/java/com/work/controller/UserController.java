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

    //�û�ע��
    @PostMapping("/register")
    public Result register(@RequestParam String username, @RequestParam String password) {
        return userService.register(username, password);
    }

    //�û���¼
    @PostMapping("/login")
    public Result login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    //����ͷ��
    @PutMapping("/updateavatar")
    public Result updateAvatar(@RequestParam String url, @RequestParam String username) {
        return userService.updateAvatar(url, username);
    }

    //��ȡ�û���Ϣ
    @GetMapping("/getinfo")
    public Result getInfo(@RequestParam String username) {
        return userService.getInfo(username);
    }

    //�޸��û���Ϣ
    @PutMapping("/updateinfo")
    public Result updateInfo(@RequestBody User user) {
        return userService.updateInfo(user);
    }

    //�û��ǳ�
    @GetMapping("/logout")
    public Result logout(@RequestParam String token, @RequestParam String username) {
        return userService.logout(token, username);
    }

    //��������
    @PutMapping("/updatepassword")
    public Result updatePassword(@RequestParam String newPassword, @RequestParam String username) {
        return userService.updatePassword(newPassword, username);
    }

    //ʵ����֤
    @GetMapping("/identify")
    public Result identify(@RequestParam String realname, @RequestParam String idnumber) {
        return userService.identify(realname, idnumber);
    }

}
