package com.work.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.work.Dao")
public class MyBatisPlusConfig {
}