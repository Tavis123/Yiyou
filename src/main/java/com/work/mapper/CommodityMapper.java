package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.pojo.Commodity;
import com.work.utils.RetCommodity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {
    @Insert("insert into commodity(commodityName, publisherId, commodityPrice, commodityAmount, publishTime,detail,isPass, commodityId, mainPicture, telephoneNumb, reviews, sales,commentAreaId,commentNum,nowCommentNum,totalSales) values (#{commodityName}, #{publisherId}, #{commodityPrice}, #{commodityAmount}, #{publishTime},#{detail},#{isPass},#{commodityId}, #{mainPicture}, #{telephoneNumb}, #{reviews}, #{sales},#{commentAreaId},#{commentNum},#{nowCommentNum},#{totalSales})")
    int addCommodity(Commodity commodity);

    @Update("update commodity set isPass = #{isPass} where commodityId = #{commodityId}")
    int changeIsPass(int isPass, String commodityId);

    @Delete("delete from commodity where commodityId = #{commodityId}")
    int deleteCommodity(String commodityId);

    //查询某个人发布的所有商品,不能查询到未上架的
    @Select("select * from commodity where publisherId = #{publisherId} and isPass = 2")
    List<Commodity> selectAllByPublishId(String publishId);

    //查询自己发布的所有商品,包括未上架的
    @Select("select * from commodity where publisherId = #{publisherId}")
    List<Commodity> selectAllSelfByPublishId(String publishId);

    //通过商品id来查询某个商品,不能查询未上架的
    @Select("select * from  commodity c left join detailpicture d on c.commodityId = d.commodityId where c.commodityId=#{commodityId} and isPass=2 union select * from commodity c right join detailpicture d on c.commodityId = d.commodityId where d.commodityId=#{commodityId} and isPass=2")
    RetCommodity selectOneByCommodityId(String commodityId);

    @Select("select * from  commodity c left join detailpicture d on c.commodityId = d.commodityId where c.commodityId=#{commodityId} and isPass=2 union select * from commodity c right join detailpicture d on c.commodityId = d.commodityId where c.commodityId=#{commodityId} and isPass=2")
    List<RetCommodity> selectOneByCommodityId1(String commodityId);

    //通过商品id来查询自己发布某个商品,可以查询未上架的
    @Select("select * from  commodity c left join detailpicture d on c.commodityId = d.commodityId where c.commodityId=#{commodityId} union select *from commodity c right join detailpicture d on c.commodityId = d.commodityId where c.commodityId=#{commodityId}")
    RetCommodity selectOneSelfByCommodityId(String commodityId);

    // List<Commodity> selectAllByPrice(int highPrice,int lowPrice);
    //查询所有商品通过价格区间
    @Select("select * from commodity where isPass = 2")
    List<Commodity> selectAll();

    @Select("select * from commodity commodityid = #{commodityId}")
    Commodity selectOneSelfByCommodityIdToMain(String commodityId);

    @Update("update commodity set sales = #{sales},commodityAmount = #{commodityAmount} where commodityId = #{commodityId}")
    int updateSalesAndAmount(int sales, int commodityAmount, String commodityId);

    @Update("update commodity set commentNum = #{commentNum} where commodityId = #{commodityId}")
    int updateCommentNum(int commentNum, String commodityId);

    @Update("update commodity set reviews = #{reviews} where commodityId = #{commodityId}")
    int updateReviews(double reviews, String commodityId);
    //  List<Commodity> selectAllByShippingLocation(String shippingLocation);
    //  List<Commodity> selectAllByTradingWay(String tradingWay);
    //  List<Commodity> selectAllByTradingPlace(String tradingPlace);

    @Update("update commodity set totalSales = #{totalSales} where commodityId = #{commodityId}")
    int updateTotalSales(int totalSales, String commodityId);

    @Update("update commodity set nowCommentNum = #{nowCommentNum} where commodityId = #{commodityId}")
    int updateNowCommentNum(int nowCommentNum, String commodityId);
}
