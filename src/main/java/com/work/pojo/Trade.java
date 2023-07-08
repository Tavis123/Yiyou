package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @TableId(type = IdType.AUTO)
    private String tradeId;//这场交易的id；
    private String customerId;//顾客账号
    private String businessManId;//商家账号
    private String commodityIdName;//商品名称
    private double totalMoney;//该交易总共所花费的钱
    private double realMoney;//商品所需付款
    private double serviceCharge;//手续费
    private int tradeNum;//交易的数量
    private int tradeStatus;//商品状态，0为待付款，1为待收货，2为已收货
    private String commodityId;//商品所属类的id;
    private String orderTime;//下单时间
    private double freight;//运费


}
