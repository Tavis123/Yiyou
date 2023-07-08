package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.pojo.Trade;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TradeMapper extends BaseMapper<Trade> {
    @Insert("insert into trade(customerId, businessManId, commodityIdName, totalMoney, realMoney, serviceCharge, tradeNum, commodityId, tradeId, orderTime,freight) value (#{customerId},#{businessManId}, #{commodityIdName}, #{totalMoney},#{realMoney}, #{serviceCharge}, #{tradeNum}, #{commodityId}, #{tradeId}, #{orderTime},#{freight})")
    int addTrade(Trade trade);

    @Update("update trade set tradeStatus = #{tradeStatus} where tradeId = #{tradeId}")
    int updateTradeStatus(int tradeStatus, String tradeId);

    @Select("select * from trade where tradeId = #{tradeId}")
    Trade selectTradeByTradeId(String tradeId);

    @Select("select * from trade where businessManId = #{businessManId}")
    List<Trade> selectAllTradeByBusinessManId(String businessManId);//�鿴�̼��Լ����˶�����Ʒ

    @Select("select * from trade where customerId = #{customerId}")
    List<Trade> selectTradeByCustomerId(String customerId);//�鿴�Լ����˶��ٶ���;

    @Delete("delete from trade where tradeId = #{tradeId}")
    int deleteTrade(String tradeId);
}
