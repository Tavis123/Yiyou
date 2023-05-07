package com.work.sys.service.impl;

import com.work.sys.entity.Users;
import com.work.sys.mapper.UsersMapper;
import com.work.sys.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-08
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
