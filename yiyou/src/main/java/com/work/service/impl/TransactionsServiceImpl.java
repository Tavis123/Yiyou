package com.work.service.impl;

import com.work.entity.Transactions;
import com.work.mapper.TransactionsMapper;
import com.work.service.ITransactionsService;
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
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions> implements ITransactionsService {

}
