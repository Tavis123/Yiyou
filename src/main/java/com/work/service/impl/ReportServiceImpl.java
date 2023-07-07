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

    // �ٱ���Ʒ
    @Override
    public Result setReport(Integer reporterid, Integer goodsid, Integer sellerid, String reason) {
        //�������Ʒ�ȼ���ٱ����ȴ�����Ա���
        Report report = new Report();
        report.setReporterid(reporterid);
        report.setGoodsid(goodsid);
        report.setSellerid(sellerid);
        report.setReason(reason);
        //����ٱ���
        reportMapper.insert(report);
        return Result.success(ResultCode.SUCCESS, "�ٱ��ɹ�,����У�");
    }

    //������Ʒid�鿴�Ƿ񱻾ٱ�
    @Override
    public Result isReport(Integer goodsid) {
        //������Ʒid�鿴�Ƿ񱻾ٱ�
        Report report = reportMapper.selectByGoodsid(goodsid);
        if (report == null) {
            return Result.error(ResultCode.ERROR, "����Ʒδ���ٱ���");
        } else {
            String reason = report.getReason();//�ٱ�ԭ��
            return Result.success(ResultCode.SUCCESS, "����Ʒ�ѱ��ٱ���", reason);
        }
    }

    // ��ȡ���б��ٱ�����Ʒ
    @Override
    public Result getAllReport() {
        //��ȡ�ٱ������е���Ʒ
        List<Report> reportList = reportMapper.selectList(null);
        return Result.success(ResultCode.SUCCESS, "��ȡ�ɹ�", reportList);
    }

    // ����Ա��˾ٱ����ظ��ٱ���
    @Override
    public Result handleReport(Integer reportid) {
        Result result;
        //��ȡ�ٱ�ԭ��
        Report report = reportMapper.selectById(reportid);
        if (report == null) {
            return Result.error(ResultCode.ERROR, "����Ʒδ���ٱ���");
        }
        String reason = report.getReason();
        //����Ա��˾ٱ�ԭ�����ж��Ƿ�ٱ��ɹ������ݹؼ��ʣ�
        if (reason.contains("ɫ��") || reason.contains("����") || reason.contains("����") || reason.contains("���") || reason.contains("��Ȩ")) {
            result = Result.success(ResultCode.SUCCESS, "�ٱ��ɹ���");
            //������id�ͱ��ٱ�ԭ����������
            Blacklist blacklist = new Blacklist();
            blacklist.setUserid(report.getSellerid());
            blacklist.setReason(reason);

            //�����ҵ��ǳƺ�ͷ�������⴦��
            String nickname = userMapper.selectById(report.getSellerid()).getNickname();
            //���ǳƵĵ�һ�������һ���ַ���������������*����
            String newNickname = nickname.substring(0, 1) + "***" + nickname.substring(nickname.length() - 1);
            //�����ҵ�ͷ����Ϊstatic���е�����ͷ��
            String newUrl = "http://localhost:8080/static/bad.jpg";

            //�����û���
            User user = userMapper.selectById(report.getSellerid());
            user.setNickname(newNickname);
            user.setAvatar(newUrl);
            //�����û���
            userMapper.updateById(user);
        } else {
            result = Result.error(ResultCode.ERROR, "�ٱ�ʧ�ܣ�");
        }
        return result;
    }
}
