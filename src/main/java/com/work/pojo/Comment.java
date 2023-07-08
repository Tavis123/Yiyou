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
    private String commentId;//评论id
    private String publisherId;//发布人id
    private String commodityId;//商品id
    private String publishTime;//发布时间
    private int likeCount;//点赞数
    private String upCommentId;//上级评论id，例如在别人的评论下答复他；
    private double reviews;//对该商品的评价；
    private String detailResponse;//详细回复；
    private int responseNum;//答复数目;
    private int isPass;//是否通过 0为不通过，1为通过
    private int nowResponseNum;

}
