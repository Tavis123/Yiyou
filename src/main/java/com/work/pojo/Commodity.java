package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    @TableId(type = IdType.AUTO)
    private String commodityId;//商品账号
    private String commodityName;
    private String publisherId; // 发布人账号
    private double commodityPrice;//  //商品价格
    private int commodityAmount;//  库存数量
    private double reviews;
    private String publishTime;//
    private String detail;
    private int isPass;// 审核是否通过 0还没审核，1审核未通过，二通过审核
    private Object mainPicture;//主界面图片
    private int sales;//商品销量
    private String commentAreaId;//评论区id
    private int commentNum;//用来标记某个评论区的评论的id，只能增加不能减少
    private int nowCommentNum;//现在评论的数量
    private int totalSales;//这个用来标记某个订单的订单号，只能增加不能减少
    private String telephoneNumb;//电话号码
}
