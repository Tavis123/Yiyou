package com.work.controller;

import com.work.common.Result;
import com.work.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/records")
public class ReportController {

    @Autowired
    private ReportService reportService;

    //用户举报违规商品
    @PostMapping("/report")
    public Result report(@RequestParam Integer reporterid, @RequestParam Integer goodsid, @RequestParam String reason) {
        Result result = reportService.setReport(reporterid, goodsid, reason);
        return result;
    }

    //根据商品id查看是否被举报
    @GetMapping("/isReport")
    public Result isReport(@RequestParam Integer goodsid) {
        Result result = reportService.isReport(goodsid);
        return result;
    }

    //获取所有被举报的商品
    @GetMapping("/getReport")
    public Result getReport() {
        Result result = reportService.getAllReport();
        return result;
    }

    //管理员审核举报并回复举报者
    @PostMapping("/handle")
    public Result handle(@RequestParam Integer reportid) {
        Result result = reportService.handleReport(reportid);
        return result;
    }
}
