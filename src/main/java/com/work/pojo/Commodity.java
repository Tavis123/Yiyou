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
    private String commodityId;//��Ʒ�˺�
    private String commodityName;
    private String publisherId; // �������˺�
    private double commodityPrice;//  //��Ʒ�۸�
    private int commodityAmount;//  �������
    private double reviews;
    private String publishTime;//
    private String detail;
    private int isPass;// ����Ƿ�ͨ�� 0��û��ˣ�1���δͨ������ͨ�����
    private Object mainPicture;//������ͼƬ
    private int sales;//��Ʒ����
    private String commentAreaId;//������id
    private int commentNum;//�������ĳ�������������۵�id��ֻ�����Ӳ��ܼ���
    private int nowCommentNum;//�������۵�����
    private int totalSales;//����������ĳ�������Ķ����ţ�ֻ�����Ӳ��ܼ���
    private String telephoneNumb;//�绰����
}
