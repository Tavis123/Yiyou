package com.work.sys.service.impl;

import com.work.sys.entity.Transactions;
import com.work.sys.mapper.TransactionsMapper;
import com.work.sys.service.ITransactionsService;
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
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions> implements ITransactionsService {

}
