package com.work.controller;

import com.work.common.Result;
import com.work.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    //�û��ٱ�Υ����Ʒ
    @PostMapping("/setreport")
    public Result report(@RequestParam Integer reporterid, @RequestParam Integer goodsid, @RequestParam Integer sellerid, @RequestParam String reason) {
        Result result = reportService.setReport(reporterid, goodsid, sellerid, reason);
        return result;
    }

    //������Ʒid�鿴�Ƿ񱻾ٱ�
    @GetMapping("/isreport")
    public Result isReport(@RequestParam Integer goodsid) {
        Result result = reportService.isReport(goodsid);
        return result;
    }

    //��ȡ���б��ٱ�����Ʒ
    @GetMapping("/getreport")
    public Result getReport() {
        Result result = reportService.getAllReport();
        return result;
    }

    //����Ա��˾ٱ����ظ��ٱ���
    @PostMapping("/handlereport")
    public Result handle(@RequestParam Integer reportid) {
        Result result = reportService.handleReport(reportid);
        return result;
    }
}
