package com.work.service.impl;

import com.work.pojo.Transactions;
import com.work.mapper.TransactionsMapper;
import com.work.service.TransactionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServiceImpl extends ServiceImpl<TransactionsMapper, Transactions> implements TransactionsService {

}
