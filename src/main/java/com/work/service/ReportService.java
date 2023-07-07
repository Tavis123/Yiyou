package com.work.service;

import com.work.common.Result;
import com.work.pojo.Report;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ReportService extends IService<Report> {
    // �ٱ���Ʒ
    Result setReport(Integer reporterid, Integer goodsid, Integer sellerid, String reason);

    // ��ȡ���б��ٱ�����Ʒ
    Result getAllReport();

    // ����Ա��˾ٱ����ظ��ٱ���
    Result handleReport(Integer reportid);

    //������Ʒid�鿴�Ƿ񱻾ٱ�
    Result isReport(Integer goodsid);
}
