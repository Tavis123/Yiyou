package com.work;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.work.entity.User;
import com.work.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@MapperScan("com.work.mapper")
public class YiYouApplicationTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public static void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    public static void main(String[] args) {
        testSelect();
    }
}