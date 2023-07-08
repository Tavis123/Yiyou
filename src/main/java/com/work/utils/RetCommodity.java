package com.work.utils;


import com.work.pojo.Commodity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetCommodity extends Commodity {
    private String commodityId;//��Ʒ�˺�
    private String commodityName;
    private String publisherId; // �������˺�
    private double commodityPrice;//  //��Ʒ�۸�
    private int commodityAmount;//  �������
    private double reviews;
    private String publishTime;//
    private String detail;
    private String telephoneNumb;
    private int isPass;// ����Ƿ�ͨ�� 0��û��ˣ�1���δͨ������ͨ�����
    private Object mainPicture;//������ͼƬ
    private int sales;//��Ʒ����
    private Object picture1;
    private Object picture2;
    private Object picture3;
    private Object picture4;
    private Object picture5;
    private String commentAreaId;
    private int totalSales;
    private int nowCommentNum;

}
