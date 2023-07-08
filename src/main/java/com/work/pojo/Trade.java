package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trade {
    @TableId(type = IdType.AUTO)
    private String tradeId;//�ⳡ���׵�id��
    private String customerId;//�˿��˺�
    private String businessManId;//�̼��˺�
    private String commodityIdName;//��Ʒ����
    private double totalMoney;//�ý����ܹ������ѵ�Ǯ
    private double realMoney;//��Ʒ���踶��
    private double serviceCharge;//������
    private int tradeNum;//���׵�����
    private int tradeStatus;//��Ʒ״̬��0Ϊ�����1Ϊ���ջ���2Ϊ���ջ�
    private String commodityId;//��Ʒ�������id;
    private String orderTime;//�µ�ʱ��
    private double freight;//�˷�


}
