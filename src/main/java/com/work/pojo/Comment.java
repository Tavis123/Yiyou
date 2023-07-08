package com.work.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @TableId(type = IdType.AUTO)
    private String commentId;//����id
    private String publisherId;//������id
    private String commodityId;//��Ʒid
    private String publishTime;//����ʱ��
    private int likeCount;//������
    private String upCommentId;//�ϼ�����id�������ڱ��˵������´�����
    private double reviews;//�Ը���Ʒ�����ۣ�
    private String detailResponse;//��ϸ�ظ���
    private int responseNum;//����Ŀ;
    private int isPass;//�Ƿ�ͨ�� 0Ϊ��ͨ����1Ϊͨ��
    private int nowResponseNum;

}
