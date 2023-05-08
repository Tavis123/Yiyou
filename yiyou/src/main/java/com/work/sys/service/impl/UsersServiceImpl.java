package com.work.sys.service.impl;

import com.work.sys.entity.Users;
import com.work.sys.mapper.UsersMapper;
import com.work.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-08
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Override
    public List<Users> getAllUsers() {
        return this.list();
    }

    @Override
    public Users getUserById(Integer id) {
        return this.getById(id);
    }

    @Override
    public boolean addUser(Users user) {
        return this.save(user);
    }

    @Override
    public boolean updateUser(Users user) {
        return this.updateById(user);
    }

    @Override
    public boolean deleteUser(Integer id) {
        return this.removeById(id);
    }
}
