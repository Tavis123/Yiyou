package com.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.work.mapper")
@ComponentScan(basePackages = {"com.work.*"})
public class YiYouApplication {
    public static void main(String[] args) {
        SpringApplication.run(YiYouApplication.class, args);
    }
}
