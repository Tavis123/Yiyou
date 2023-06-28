package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Transactions implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer buyerId;

    private Integer sellerId;

    private Integer gameAccountId;

    private BigDecimal amount;

    private LocalDateTime transactionTime;

    private String status;
}
