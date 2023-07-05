package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data//举报表
public class Report implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer reportid;//举报id

    private Integer reporterid;//举报者id

    private Integer sellerid;//卖家id

    private Integer goodsid;//商品id

    private String reason;

}
