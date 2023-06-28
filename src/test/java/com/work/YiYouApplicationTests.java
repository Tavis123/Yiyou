package com.work;

import com.work.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@MapperScan("com.work.mapper")
public class YiYouApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        userMapper.selectList(null).forEach(System.out::println);
    }

}