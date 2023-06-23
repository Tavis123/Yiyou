package com.work.controller;

import com.work.entity.User;
import com.work.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@RestController
@RequestMapping("/system/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public User login(@RequestBody String username, @RequestBody String password) {
        return userService.login(username, password);
    }

    @PostMapping("/register")
    public boolean register(@RequestBody String username, @RequestBody String password) {
        return userService.register(username, password);
    }
}
