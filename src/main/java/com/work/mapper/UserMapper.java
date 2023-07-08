package com.work.mapper;

import com.work.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

//一般来说，增删改方法的返回值都是int，表示操作成功记录条数，查方法一般是返回相应对象或者对象的List。
@Mapper
public interface UserMapper extends BaseMapper<User> {

    //根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    //通过账号来查询某个账户
    @Select("select * from user where userId = #{userId}")
    User selectUserById(String userId);

    //改变某个账号的发布商品数目
    @Update("update user set commodityNum = #{commodityNum} where userId = #{userId}")
    int updateNum(int commodityNum, String userId);

    //改变某个账号的余额
    @Update("update user set money = #{money} where userId = #{userId}")
    int updateMoney(double money, String userId);

    //根据用户名查询对应的头像
    @Select("select avatar from user where username = #{username};")
    String selectAvatarByUsername(String username);

}
