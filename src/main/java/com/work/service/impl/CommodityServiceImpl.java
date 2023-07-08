package com.work.service.impl;


import com.work.mapper.CommodityMapper;
import com.work.pojo.Commodity;
import com.work.service.CommodityService;
import com.work.utils.RetCommodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public int addCommodity(Commodity commodity) {
        return commodityMapper.addCommodity(commodity);
    }

    @Override
    public int changeIsPass(int isPass, String commodityId) {
        return commodityMapper.changeIsPass(isPass, commodityId);
    }

    @Override
    public int deleteCommodity(String commodityId) {
        return commodityMapper.deleteCommodity(commodityId);
    }

    @Override
    public List<Commodity> selectAllByPublishId(String publisherId) {
        return commodityMapper.selectAllByPublishId(publisherId);
    }//查询某个人发布的所有商品,不能查询到未上架的

    //查询自己发布的所有商品,包括未上架的
    @Override
    public List<Commodity> selectAllSelfByPublishId(String publisherId) {
        return commodityMapper.selectAllSelfByPublishId(publisherId);
    }

    //通过商品id来查询某个商品,不能查询未上架的
    @Override
    public RetCommodity selectOneByCommodityId(String commodityId) {
        return commodityMapper.selectOneByCommodityId(commodityId);
    }

    @Override
    public List<RetCommodity> selectOneByCommodityId1(String commodityId) {
        return commodityMapper.selectOneByCommodityId1(commodityId);
    }

    //通过商品id来查询自己发布某个商品,可以查询未上架的
    @Override
    public RetCommodity selectOneSelfByCommodityId(String commodityId) {
        return commodityMapper.selectOneSelfByCommodityId(commodityId);
    }

    @Override
    public List<Commodity> selectAll() {
        return commodityMapper.selectAll();
    }

    @Override
    public Commodity selectOneSelfByCommodityIdToMain(String commodityId) {
        return commodityMapper.selectOneSelfByCommodityIdToMain(commodityId);
    }

    @Override
    public int updateSalesAndAmount(int sales, int commodityAmount, String commodityId) {
        return commodityMapper.updateSalesAndAmount(sales, commodityAmount, commodityId);
    }

    @Override
    public int updateCommentNum(int commentNum, String commodityId) {
        return commodityMapper.updateCommentNum(commentNum, commodityId);
    }

    @Override
    public int updateTotalSales(int totalSales, String commodityId) {
        return commodityMapper.updateTotalSales(totalSales, commodityId);
    }

    @Override
    public int updateNowCommentNum(int nowCommentNum, String commodityId) {
        return commodityMapper.updateNowCommentNum(nowCommentNum, commodityId);
    }

    @Override
    public int updateReviews(double reviews, String commodityId) {
        Commodity commodity = commodityMapper.selectOneByCommodityId(commodityId);
        double reviews1 = (commodity.getReviews() * (commodity.getNowCommentNum()) + reviews) / (commodity.getNowCommentNum() + 1);
        return commodityMapper.updateReviews(reviews1, commodityId);
    }
}
