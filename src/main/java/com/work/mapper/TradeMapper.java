package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.pojo.Trade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TradeMapper extends BaseMapper<Trade> {
    @Insert("insert into trade(customerId, businessManId, commodityIdName,totalMoney,realMoney,serviceCharge,tradeNum,tradeId,orderTime,freight) values (#{customerId}, #{businessManId}, #{commodityIdName}, #{totalMoney}, #{realMoney}, #{serviceCharge}, #{tradeNum}, #{tradeId}, #{orderTime}, #{freight})")
    int addTrade(Trade trade);

    @Update("update trade set tradeStatus = #{tradeStatus} where tradeId = #{tradeId}")
    int updateTradeStatus(int tradeStatus, String tradeId);

    @Select("select * from trade where tradeId = #{tradeId}")
    Trade selectTradeByTradeId(String tradeId);

    @Select("select * from trade where businessManId = #{businessManId}")
    List<Trade> selectAllTradeByBusinessManId(String businessManId);//查看商家自己卖了多少商品

    @Select("select * from trade where customerId = #{customerId}")
    List<Trade> selectTradeByCustomerId(String customerId);//查看自己下了多少订单;

    @Delete("delete from trade where tradeId = #{tradeId}")
    int deleteTrade(String tradeId);
}
