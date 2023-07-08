package com.work.service;

import com.work.pojo.Trade;

import java.util.List;

public interface TradeService {
    int addTrade(Trade trade);

    int updateTradeStatus(int tradeStatus, String tradeId);

    Trade selectTradeByTradeId(String tradeId);

    List<Trade> selectAllTradeByBusinessManId(String businessManId);//查看商家自己卖了多少商品

    List<Trade> selectTradeByCustomerId(String customerId);//查看自己下了多少订单;

    int deleteTrade(String tradeId);
}
