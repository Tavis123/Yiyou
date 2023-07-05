package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data//�ٱ���
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer reportid;//�ٱ�id

    private Integer reporterid;//�ٱ���id

    private Integer sellerid;//����id

    private Integer goodsid;//��Ʒid

    private String reason;

}
