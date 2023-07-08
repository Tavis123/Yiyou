package com.work.service;

import com.work.pojo.Trade;

import java.util.List;

public interface TradeService {
    int addTrade(Trade trade);

    int updateTradeStatus(int tradeStatus, String tradeId);

    Trade selectTradeByTradeId(String tradeId);

    List<Trade> selectAllTradeByBusinessManId(String businessManId);//�鿴�̼��Լ����˶�����Ʒ

    List<Trade> selectTradeByCustomerId(String customerId);//�鿴�Լ����˶��ٶ���;

    int deleteTrade(String tradeId);
}
