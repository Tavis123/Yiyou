package com.work.service;

import com.work.common.Result;
import com.work.pojo.Report;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReportService extends IService<Report> {
    // 举报商品
    Result setReport(Integer reporterid, Integer goodsid, String reason);

    // 获取所有被举报的商品
    Result getAllReport();

    // 管理员审核举报并回复举报者
    Result handleReport(Integer reportid);

    //根据商品id查看是否被举报
    Result isReport(Integer goodsid);
}
