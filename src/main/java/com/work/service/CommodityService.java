package com.work.service;

import com.work.pojo.Commodity;
import com.work.utils.RetCommodity;

import java.util.List;

public interface CommodityService {
    int addCommodity(Commodity commodity);

    int changeIsPass(int isPass, String commodityId);

    int deleteCommodity(String commodityId);

    //��ѯĳ���˷�����������Ʒ,���ܲ�ѯ��δ�ϼܵ�
    List<Commodity> selectAllByPublishId(String publisherId);

    //��ѯ�Լ�������������Ʒ,����δ�ϼܵ�
    List<Commodity> selectAllSelfByPublishId(String publisherId);

    //ͨ����Ʒid����ѯĳ����Ʒ,���ܲ�ѯδ�ϼܵ�
    RetCommodity selectOneByCommodityId(String commodityId);

    List<RetCommodity> selectOneByCommodityId1(String commodityId);

    //ͨ����Ʒid����ѯ�Լ�����ĳ����Ʒ,���Բ�ѯδ�ϼܵ�
    RetCommodity selectOneSelfByCommodityId(String commodityId);

    List<Commodity> selectAll();

    Commodity selectOneSelfByCommodityIdToMain(String commodityId);

    int updateSalesAndAmount(int sales, int commodityAmount, String commodityId);

    int updateCommentNum(int commentNum, String commodityId);

    int updateReviews(double reviews, String commodityId);

    int updateTotalSales(int totalSales, String commodityId);

    int updateNowCommentNum(int nowCommentNum, String commodityId);

}
