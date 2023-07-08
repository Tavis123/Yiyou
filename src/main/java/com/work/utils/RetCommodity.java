package com.work.utils;


import com.work.pojo.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetCommodity extends Commodity {
    private String commodityId;//商品账号
    private String commodityName;
    private String publisherId; // 发布人账号
    private double commodityPrice;//  //商品价格
    private int commodityAmount;//  库存数量
    private double reviews;
    private String publishTime;//
    private String detail;
    private String telephoneNumb;
    private int isPass;// 审核是否通过 0还没审核，1审核未通过，二通过审核
    private Object mainPicture;//主界面图片
    private int sales;//商品销量
    private Object picture1;
    private Object picture2;
    private Object picture3;
    private Object picture4;
    private Object picture5;
    private String commentAreaId;
    private int totalSales;
    private int nowCommentNum;

}
