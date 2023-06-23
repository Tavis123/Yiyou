package com.work.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("accident_handling")
public class Handling implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String accidentType;

    private String result;

    private LocalDateTime handlingTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    public LocalDateTime getHandlingTime() {
        return handlingTime;
    }

    public void setHandlingTime(LocalDateTime handlingTime) {
        this.handlingTime = handlingTime;
    }

    @Override
    public String toString() {
        return "Handling{" +
            "id=" + id +
            ", accidentType=" + accidentType +
            ", result=" + result +
            ", handlingTime=" + handlingTime +
        "}";
    }
}
