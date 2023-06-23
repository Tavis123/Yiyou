package com.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
public class Transactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer buyerId;

    private Integer sellerId;

    private Integer gameAccountId;

    private BigDecimal amount;

    private LocalDateTime transactionTime;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }
    public Integer getGameAccountId() {
        return gameAccountId;
    }

    public void setGameAccountId(Integer gameAccountId) {
        this.gameAccountId = gameAccountId;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transactions{" +
            "id=" + id +
            ", buyerId=" + buyerId +
            ", sellerId=" + sellerId +
            ", gameAccountId=" + gameAccountId +
            ", amount=" + amount +
            ", transactionTime=" + transactionTime +
            ", status=" + status +
        "}";
    }
}
