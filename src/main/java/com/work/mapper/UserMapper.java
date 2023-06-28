package com.work.mapper;

import com.work.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

//一般来说，增删改方法的返回值都是int，表示操作成功记录条数，查方法一般是返回相应对象或者对象的List。
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    //根据用户名查询用户
//    @Select("select * from user where username = #{username}")
//    User selectByUsername(String username);
//
//    //新增用户
//    @Insert("insert into user(username,password,contact_info) values(#{username},#{password},#{contactInfo})")
//    int add(User user);
//
//    //修改用户信息
//    @Update("update user set username=#{username},password=#{password},contact_info=#{contactInfo} where id=#{id}")
//    int update(User user);
//
//    //删除用户
//    @Delete("delete from user where id=#{id}")
//    int delete(Integer id);
//
//    //根据id获取用户
//    @Select("select * from user where id=#{id}")
//    User getById(Integer id);
//
//    //根据用户名获取用户
//    @Select("select * from user where username=#{username}")
//    User getByUsername(String username);


}
