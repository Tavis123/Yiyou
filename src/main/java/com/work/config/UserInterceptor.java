package com.work.config;

import com.work.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {
    @Autowired
    private UserServiceImpl userService;

    //Controller方法执行前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //同样在这里调用用户服务传入session，判断用户是否登录或者有效
        //若用户未登录，则重定向至主页（假设主页就是/）
        if (!userService.isLogin(request.getSession()).isSuccess()) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    //Controller方法执行后调用
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    //整个请求完成后（包括Thymeleaf渲染完毕）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
/*
虽然有判断用户登录的API，但是如果页面很多，每一个都要判断登录，就会很麻烦。
通过拦截器即可对指定的路径设定拦截点。
 */