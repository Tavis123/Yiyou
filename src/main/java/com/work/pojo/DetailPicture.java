package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailPicture {
    @TableId(type = IdType.AUTO)
    private String commodityId;
    private Object picture1;
    private Object picture2;
    private Object picture3;
    private Object picture4;
    private Object picture5;
}
