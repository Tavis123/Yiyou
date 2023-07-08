package com.work.controller;

import com.work.pojo.Commodity;
import com.work.pojo.Trade;
import com.work.pojo.User;
import com.work.service.CommodityService;
import com.work.service.TradeService;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Trade")
public class TradeController {
    @Autowired
    CommodityService commodityService;
    @Autowired
    UserService userService;
    @Autowired
    TradeService tradeService;

    @PostMapping("/addTrade")
    public Map<String, String> addTrade(Trade trade) {
        Map<String, String> map = new HashMap<>();
        User customer = userService.selectUserById(trade.getCustomerId());
        User businessMan = userService.selectUserById(trade.getBusinessManId());
        System.out.println(trade.getCommodityId());
        Commodity commodity = commodityService.selectOneByCommodityId(trade.getCommodityId());
        System.out.println("asda123123");
        if (commodity.getCommodityAmount() - trade.getTradeNum() < 0) {
            map.put("msg", "该商品库存不足");
            return map;
        }
        System.out.println("123");
        trade.setFreight(10);//默认运费为十元
        trade.setRealMoney(commodity.getCommodityPrice());
        trade.setServiceCharge(trade.getRealMoney() * 0.01 * trade.getTradeNum());//手续费为商品的百分之一
        trade.setTotalMoney((trade.getRealMoney() + trade.getServiceCharge()) * trade.getTradeNum() + trade.getFreight());
        trade.setTradeStatus(0);
        trade.setCommodityIdName(commodity.getCommodityName());
        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        trade.setOrderTime(sdf.format(day));//获得当前时间
        System.out.println(trade.getRealMoney());
        trade.setTradeId(commodity.getCommodityId() + "#" + commodity.getTotalSales());
        commodityService.updateTotalSales(commodity.getTotalSales() + 1, commodity.getCommodityId());
        //商品的库存和销量先改变防止没有库存
        System.out.println(commodity.getSales() + trade.getTradeNum());
        System.out.println(commodity.getCommodityAmount() - trade.getTradeNum());
        commodityService.updateSalesAndAmount(commodity.getSales() + trade.getTradeNum(),
                commodity.getCommodityAmount() - trade.getTradeNum(), commodity.getCommodityId());
        tradeService.addTrade(trade);
        map.put("msg", "下单成功");
        return map;
    }

    @PostMapping("/payment")
    public Map<String, String> payment(String tradeId) {
        Map<String, String> map = new HashMap<>();
        Trade trade = tradeService.selectTradeByTradeId(tradeId);
        User customer = userService.selectUserById(trade.getCustomerId());
        User businessMan = userService.selectUserById(trade.getBusinessManId());
        Commodity commodity = commodityService.selectOneByCommodityId(trade.getCommodityId());
        System.out.println(customer.getMoney() - trade.getTotalMoney());
        if (customer.getMoney() - trade.getTotalMoney() < 0) {
            map.put("msg", "账户余额不足，请充值");
            return map;
        }
        //资金冻结
        userService.updateMoney(customer.getMoney() - trade.getTotalMoney(), String.valueOf(customer.getId()));
        //改变订单状态
        tradeService.updateTradeStatus(1, tradeId);
        userService.updateMoney(trade.getServiceCharge(), "110");
        map.put("msg", "付款成功!");
        return map;
    }

    @PostMapping("/confirmReceipt")
    public Map<String, String> confirmReceipt(String tradeId) {
        Trade trade = tradeService.selectTradeByTradeId(tradeId);
        tradeService.updateTradeStatus(2, tradeId);
        User businessManId = userService.selectUserById(trade.getBusinessManId());
        userService.updateMoney(trade.getRealMoney() + trade.getFreight(), String.valueOf(businessManId.getId()));
        Map<String, String> map = new HashMap<>();
        map.put("msg", "收货成功！");
        return map;
    }

    @GetMapping("/selectTradeByTradeId")
    public Trade selectTradeByTradeId(String tradeId) {
        return tradeService.selectTradeByTradeId(tradeId);
    }

    @GetMapping("/selectTradeByBusinessManId")
    public List<Trade> selectAllTradeByBusinessManId(String businessManId)//查看商家自己出售那些商品
    {
        return tradeService.selectAllTradeByBusinessManId(businessManId);
    }

    @GetMapping("/selectTradeByCustomerId")
    public List<Trade> selectTradeByCustomerId(String customerId)//查看自己下了多少订单
    {
        return tradeService.selectTradeByCustomerId(customerId);
    }

    @PostMapping("/deleteTrade")
    public Map<String, String> deleteTrade(String tradeId) {
        Trade trade = tradeService.selectTradeByTradeId(tradeId);
        if (trade.getTradeStatus() == 0) {
            //还没付款
            User businessMan = userService.selectUserById(trade.getBusinessManId());
            Commodity commodity = commodityService.selectOneByCommodityId(trade.getCommodityId());
            commodityService.updateSalesAndAmount(commodity.getSales() - 1, commodity.getCommodityAmount() + 1, commodity.getCommodityId());
        }
        tradeService.deleteTrade(tradeId);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "删除成功！");
        return map;
    }
}
