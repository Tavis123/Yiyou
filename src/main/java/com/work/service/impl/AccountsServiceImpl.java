package com.work.service.impl;

import com.work.pojo.Accounts;
import com.work.mapper.AccountsMapper;
import com.work.service.IAccountsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
@Service
public class AccountsServiceImpl extends ServiceImpl<AccountsMapper, Accounts> implements IAccountsService {

}
