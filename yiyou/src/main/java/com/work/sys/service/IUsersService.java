package com.work.sys.service;

import com.work.sys.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-08
 */
public interface IUsersService extends IService<Users> {

    List<Users> getAllUsers();

    Users getUserById(Integer id);

    boolean addUser(Users user);

    boolean updateUser(Users user);

    boolean deleteUser(Integer id);
}
