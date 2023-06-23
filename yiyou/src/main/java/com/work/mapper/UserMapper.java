package com.work.mapper;

import com.work.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    //根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);


}
