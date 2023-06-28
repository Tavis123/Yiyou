package com.work.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wq
 * @desc 注册拦截器
 */
@Configuration
public class InterceptorRegister implements WebMvcConfigurer {
    @Bean
    public HandlerInterceptor getInterceptor() {
        return new UserInterceptor();
    }

    //添加拦截器，配置拦截地址
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> pathPatterns = new ArrayList<>();
        pathPatterns.add("/update");
        registry.addInterceptor(getInterceptor()).addPathPatterns(pathPatterns);
    }
}
