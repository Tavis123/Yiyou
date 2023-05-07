package com.work;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.work.*.Dao")
public class YiYouApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiYouApplication.class, args);
    }

}