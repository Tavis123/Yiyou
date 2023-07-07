package com.work.service.impl;

import com.work.common.ResultCode;
import com.work.common.Result;
import com.work.mapper.UserMapper;
import com.work.pojo.Blacklist;
import com.work.pojo.Report;
import com.work.mapper.ReportMapper;
import com.work.pojo.User;
import com.work.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private UserMapper userMapper;

    // 举报商品
    @Override
    public Result setReport(Integer reporterid, Integer goodsid, Integer sellerid, String reason) {
        //将这个商品先加入举报表，等待管理员审核
        Report report = new Report();
        report.setReporterid(reporterid);
        report.setGoodsid(goodsid);
        report.setSellerid(sellerid);
        report.setReason(reason);
        //存入举报表
        reportMapper.insert(report);
        return Result.success(ResultCode.SUCCESS, "举报成功,审核中！");
    }

    //根据商品id查看是否被举报
    @Override
    public Result isReport(Integer goodsid) {
        //根据商品id查看是否被举报
        Report report = reportMapper.selectByGoodsid(goodsid);
        if (report == null) {
            return Result.error(ResultCode.ERROR, "该商品未被举报！");
        } else {
            String reason = report.getReason();//举报原因
            return Result.success(ResultCode.SUCCESS, "该商品已被举报！", reason);
        }
    }

    // 获取所有被举报的商品
    @Override
    public Result getAllReport() {
        //获取举报表所有的商品
        List<Report> reportList = reportMapper.selectList(null);
        return Result.success(ResultCode.SUCCESS, "获取成功", reportList);
    }

    // 管理员审核举报并回复举报者
    @Override
    public Result handleReport(Integer reportid) {
        Result result;
        //获取举报原因
        Report report = reportMapper.selectById(reportid);
        if (report == null) {
            return Result.error(ResultCode.ERROR, "该商品未被举报！");
        }
        String reason = report.getReason();
        //管理员审核举报原因来判断是否举报成功（根据关键词）
        if (reason.contains("色情") || reason.contains("暴力") || reason.contains("政治") || reason.contains("外挂") || reason.contains("侵权")) {
            result = Result.success(ResultCode.SUCCESS, "举报成功！");
            //将卖家id和被举报原因加入黑名单
            Blacklist blacklist = new Blacklist();
            blacklist.setUserid(report.getSellerid());
            blacklist.setReason(reason);

            //将卖家的昵称和头像作特殊处理
            String nickname = userMapper.selectById(report.getSellerid()).getNickname();
            //将昵称的第一个和最后一个字符保留，其他的用*代替
            String newNickname = nickname.substring(0, 1) + "***" + nickname.substring(nickname.length() - 1);
            //将卖家的头像设为static包中的特殊头像
            String newUrl = "http://localhost:8080/static/bad.jpg";

            //更新用户表
            User user = userMapper.selectById(report.getSellerid());
            user.setNickname(newNickname);
            user.setAvatar(newUrl);
            //更新用户表
            userMapper.updateById(user);
        } else {
            result = Result.error(ResultCode.ERROR, "举报失败！");
        }
        return result;
    }
}
