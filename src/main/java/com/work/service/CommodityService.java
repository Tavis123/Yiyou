package com.work.service;

import com.work.pojo.Commodity;
import com.work.utils.RetCommodity;

import java.util.List;

public interface CommodityService {
    int addCommodity(Commodity commodity);

    int changeIsPass(int isPass, String commodityId);

    int deleteCommodity(String commodityId);

    //查询某个人发布的所有商品,不能查询到未上架的
    List<Commodity> selectAllByPublishId(String publisherId);

    //查询自己发布的所有商品,包括未上架的
    List<Commodity> selectAllSelfByPublishId(String publisherId);

    //通过商品id来查询某个商品,不能查询未上架的
    RetCommodity selectOneByCommodityId(String commodityId);

    List<RetCommodity> selectOneByCommodityId1(String commodityId);

    //通过商品id来查询自己发布某个商品,可以查询未上架的
    RetCommodity selectOneSelfByCommodityId(String commodityId);

    List<Commodity> selectAll();

    Commodity selectOneSelfByCommodityIdToMain(String commodityId);

    int updateSalesAndAmount(int sales, int commodityAmount, String commodityId);

    int updateCommentNum(int commentNum, String commodityId);

    int updateReviews(double reviews, String commodityId);

    int updateTotalSales(int totalSales, String commodityId);

    int updateNowCommentNum(int nowCommentNum, String commodityId);

}
