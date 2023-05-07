package com.work.sys.service.impl;

import com.work.sys.entity.Accounts;
import com.work.sys.mapper.AccountsMapper;
import com.work.sys.service.IAccountsService;
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
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, Accounts> implements IAccountsService {

}
