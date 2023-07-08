package com.work.controller;

import com.work.pojo.Commodity;
import com.work.pojo.User;
import com.work.service.CommodityService;
import com.work.service.DetailPictureService;
import com.work.service.UserService;
import com.work.utils.RetCommodity;
import com.work.utils.fileutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/Commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;
    @Autowired
    UserService userService;
    @Autowired
    DetailPictureService detailPictureService;

    @PostMapping("/addCommodity")
    public Map<String, Object> addCommodity(@RequestParam(value = "file", required = false) MultipartFile file, Commodity commodity) throws IOException {
        System.out.println(commodity);
        BASE64Encoder encoder = new BASE64Encoder();
        Map<String, Object> map = new HashMap<>();

        String image1 = "";
        if (file != null) {
            image1 = encoder.encode(file.getBytes());
        }
        Date day = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        commodity.setPublishTime(sdf.format(day));
        User user = userService.selectUserById(commodity.getPublisherId());
        System.out.println(user);
        String commodityId = commodity.getPublisherId() + "#" + user.getCommodityNum();
        commodity.setCommodityId(commodityId);
        commodity.setCommentAreaId(commodity.getCommodityId() + "$" + user.getCommodityNum());
        userService.updateNum(user.getCommodityNum() + 1, String.valueOf(user.getId()));
        commodity.setMainPicture(image1);
        commodity.setTotalSales(0);
        commodity.setNowCommentNum(0);
        commodity.setSales(0);
        commodityService.addCommodity(commodity);
        map.put("msg", "添加项目成功，等待审核");
        return map;
    }

    @PostMapping("/changeIsPass")
    public Map<String, String> changeIsPass(int isPass, String commodityId) {
        commodityService.changeIsPass(isPass, commodityId);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "更改成功");
        return map;
    }

    @PostMapping("/deleteCommodity")
    public Map<String, String> deleteCommodity(String commodityId) {
        commodityService.deleteCommodity(commodityId);
        detailPictureService.deleteDetailPicture(commodityId);
        Map<String, String> map = new HashMap<>();
        map.put("msg", "删除成功");
        return map;
    }

    @GetMapping("/selectAllByPublishId")
    public List<Commodity> selectAllByPublishId(String publisherId) throws IOException {

        List<Commodity> list = commodityService.selectAllByPublishId(publisherId);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            fileutil fileutil = new fileutil();
            byte[] bytes = (byte[]) list.get(i).getMainPicture();
            bytes = fileutil.fileUtil(bytes);
            list.get(i).setMainPicture(bytes);
        }
        return list;
    }//查询某个人发布的所有商品,不能查询到未上架的

    //查询自己发布的所有商品,包括未上架的
    @GetMapping("/selectAllSelfByPublishId")
    public List<Commodity> selectAllSelfByPublishId(String publisherId) throws IOException {
        List<Commodity> list = commodityService.selectAllSelfByPublishId(publisherId);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            fileutil fileutil = new fileutil();
            byte[] bytes = (byte[]) list.get(i).getMainPicture();
            bytes = fileutil.fileUtil(bytes);
            list.get(i).setMainPicture(bytes);
        }
        return list;
    }

    //通过商品id来查询某个商品,不能查询未上架的
    @GetMapping("/selectOneByCommodityId")
    public RetCommodity selectOneByCommodityId(String commodityId) throws IOException {
        System.out.println(commodityId);
        RetCommodity retCommodity = commodityService.selectOneByCommodityId(commodityId);
        System.out.println(retCommodity);
        fileutil fileutil = new fileutil();
        byte[] bytes1 = (byte[]) retCommodity.getPicture1();
        byte[] bytes2 = (byte[]) retCommodity.getPicture2();
        byte[] bytes3 = (byte[]) retCommodity.getPicture3();
        byte[] bytes4 = (byte[]) retCommodity.getPicture4();
        byte[] bytes5 = (byte[]) retCommodity.getPicture5();
        byte[] bytes6 = (byte[]) retCommodity.getMainPicture();
        if (bytes1 != null) {
            bytes1 = fileutil.fileUtil(bytes1);
        }
        retCommodity.setPicture1(bytes1);
        if (bytes2 != null) {
            bytes2 = fileutil.fileUtil(bytes2);
        }
        retCommodity.setPicture2(bytes2);
        if (bytes3 != null) {
            bytes3 = fileutil.fileUtil(bytes3);
        }
        retCommodity.setPicture3(bytes3);
        if (bytes4 != null) {
            bytes4 = fileutil.fileUtil(bytes4);
        }
        retCommodity.setPicture4(bytes4);
        if (bytes5 != null) {
            bytes5 = fileutil.fileUtil(bytes5);
        }
        retCommodity.setPicture5(bytes5);
        if (bytes6 != null) {
            bytes6 = fileutil.fileUtil(bytes6);
        }
        retCommodity.setMainPicture(bytes6);

        return retCommodity;
    }

    //通过商品id来查询自己发布某个商品,可以查询未上架的
    @GetMapping("/selectOneSelfByCommodityId")
    public RetCommodity selectOneSelfByCommodityId(String commodityId) throws IOException {
        RetCommodity retCommodity = commodityService.selectOneSelfByCommodityId(commodityId);
        fileutil fileutil = new fileutil();
        byte[] bytes1 = (byte[]) retCommodity.getPicture1();
        byte[] bytes2 = (byte[]) retCommodity.getPicture2();
        byte[] bytes3 = (byte[]) retCommodity.getPicture3();
        byte[] bytes4 = (byte[]) retCommodity.getPicture4();
        byte[] bytes5 = (byte[]) retCommodity.getPicture5();
        byte[] bytes6 = (byte[]) retCommodity.getMainPicture();
        if (bytes1 != null) {
            bytes1 = fileutil.fileUtil(bytes1);
        }
        retCommodity.setPicture1(bytes1);
        if (bytes2 != null) {
            bytes2 = fileutil.fileUtil(bytes2);
        }
        retCommodity.setPicture2(bytes2);
        if (bytes3 != null) {
            bytes3 = fileutil.fileUtil(bytes3);
        }
        retCommodity.setPicture3(bytes3);
        if (bytes4 != null) {
            bytes4 = fileutil.fileUtil(bytes4);
        }
        retCommodity.setPicture4(bytes4);
        if (bytes5 != null) {
            bytes5 = fileutil.fileUtil(bytes5);
        }
        retCommodity.setPicture5(bytes5);
        if (bytes6 != null) {
            bytes6 = fileutil.fileUtil(bytes6);
        }
        retCommodity.setMainPicture(bytes6);
        return retCommodity;
    }

    @GetMapping("/selectAll")
    public List<Commodity> selectAll(String keyWord, int lowPrice, int highPrice, String tradeWay, String tradePlace, int sales) throws IOException {
        List<Commodity> list = commodityService.selectAll();
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            fileutil fileutil = new fileutil();
            byte[] bytes = (byte[]) list.get(i).getMainPicture();
            bytes = fileutil.fileUtil(bytes);
            list.get(i).setMainPicture(bytes);
        }
        List<Commodity> l1 = new ArrayList<>();
        System.out.println(keyWord + "   " + tradePlace);
        System.out.println(tradePlace);
        for (int i = 0; i < list.size(); i++) {
            Commodity commodity = list.get(i);
            if ((commodity.getCommodityName().contains(keyWord)) && (commodity.getCommodityPrice() >= lowPrice && commodity.getCommodityPrice() <= highPrice)
                    && commodity.getSales() >= sales) {
                l1.add(commodity);
            }
        }

        System.out.println(l1.size());
        return l1;
    }
//    @RequestMapping("/decode")
//    public Map<String,Object> decodeCommodity(String commodityId) throws IOException {
//        Map<String,Object> map=new HashMap<>();
//        fileutil fileutil=new fileutil();
//        Commodity commodity=commodityService.selectOneSelfByCommodityIdToMain(commodityId);
//        byte[] bytes=(byte[])commodity.getMainPicture();
//        bytes=fileutil.fileUtil(bytes);
//        commodity.setMainPicture(bytes);
//        map.put("commodity",commodity);
//        return map;
//    }
}
