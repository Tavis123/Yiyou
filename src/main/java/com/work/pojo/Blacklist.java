package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data//��������
public class Blacklist implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;//������id

    private Integer userid;//���ٱ��ɹ����û�id

    private String reason;//�ٱ�ԭ��
}
