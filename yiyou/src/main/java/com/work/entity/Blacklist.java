package com.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author wuqi
 * @since 2023-05-13
 */
public class Blacklist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String reason;

    private LocalDateTime blacklistedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDateTime getBlacklistedTime() {
        return blacklistedTime;
    }

    public void setBlacklistedTime(LocalDateTime blacklistedTime) {
        this.blacklistedTime = blacklistedTime;
    }

    @Override
    public String toString() {
        return "Blacklist{" +
            "id=" + id +
            ", userId=" + userId +
            ", reason=" + reason +
            ", blacklistedTime=" + blacklistedTime +
        "}";
    }
}
