package com.work.service.impl;


import com.work.mapper.TradeMapper;
import com.work.pojo.Trade;
import com.work.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    TradeMapper tradeMapper;

    @Override
    public int addTrade(Trade trade) {
        return tradeMapper.addTrade(trade);
    }

    @Override
    public int updateTradeStatus(int tradeStatus, String tradeId) {
        return tradeMapper.updateTradeStatus(tradeStatus, tradeId);
    }

    @Override
    public Trade selectTradeByTradeId(String tradeId) {
        return tradeMapper.selectTradeByTradeId(tradeId);
    }

    //�鿴�̼��Լ����˶�����Ʒ
    @Override
    public List<Trade> selectAllTradeByBusinessManId(String businessManId)
    {
        return tradeMapper.selectAllTradeByBusinessManId(businessManId);
    }

    //�鿴�Լ����˶��ٶ���
    @Override
    public List<Trade> selectTradeByCustomerId(String customerId)
    {
        return tradeMapper.selectTradeByCustomerId(customerId);
    }

    @Override
    public int deleteTrade(String tradeId) {
        return tradeMapper.deleteTrade(tradeId);
    }
}
