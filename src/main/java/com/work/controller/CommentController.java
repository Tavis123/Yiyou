package com.work.controller;


import com.work.pojo.Comment;
import com.work.pojo.Commodity;
import com.work.service.CommentService;
import com.work.service.CommodityService;
import com.work.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/Comment")
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    UserService userService;

    @PostMapping("/addComment")
    public Map<String, String> addCommment(Comment comment) {
        Commodity commodity = commodityService.selectOneByCommodityId(comment.getCommodityId());
        Map<String, String> map = new HashMap<>();
        if (comment.getUpCommentId().equals(commodityService.selectOneByCommodityId(comment.getCommodityId()).getCommentAreaId())) {
            //如果相等说明这是在一个商品下的评论
            //此时评论的id为商品评论区的id加上该商品的评论数
            comment.setCommentId(comment.getUpCommentId() + "%" + commodity.getCommentNum());
            commodityService.updateCommentNum(commodity.getCommentNum() + 1, commodity.getCommodityId());
            comment.setIsPass(0);
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setPublishTime(sdf.format(day));
        } else {
            //不想等说明这是在一个评论下的评论
            //此时评论的id为上级评论的id加上回复上级评论的个数
            comment.setCommentId(comment.getUpCommentId() + "%" + comment.getResponseNum());
            comment.setIsPass(0);
            //评论回复数加1
            commentService.updateResponseNum(comment.getResponseNum() + 1, comment.getCommentId());
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setPublishTime(sdf.format(day));
        }
        comment.setIsPass(0);
        comment.setNowResponseNum(0);
        comment.setLikeCount(0);
        commentService.addCommment(comment);
        map.put("msg", "评价成功！");
        return map;

    }

    @DeleteMapping("/deleteComment")
    public Map<String, String> deleteComment(String commentId) {
        //这会导致删除的评论的评分没有删去后期酌情修改
        Map<String, String> map = new HashMap<>();
        map.put("msg", "删除成功!");
        List<Comment> commentList = commentService.selectAllPassCommentByUpCommentId(commentId);
        Comment comment = commentService.selectCommentByCommentId(commentId);
        Commodity commodity = commodityService.selectOneByCommodityId(comment.getCommodityId());
        if (comment.getUpCommentId().equals(commodity.getCommentAreaId())) {
            //说明这个评论属于某商品的评论区，此时要删除该评论就必须减少该商品的当前评论数
            commodityService.updateNowCommentNum(commodity.getCommentNum() - 1, commodity.getCommodityId());
        } else {
            //说明该评论属于某个评论下的评论，此时要减少的是上级评论的当前回复数
            Comment upComment = commentService.selectCommentByCommentId(comment.getUpCommentId());
            commentService.updateResponseNum(upComment.getNowResponseNum() - 1, upComment.getUpCommentId());
        }
        //这段会有bug例如把本级的清空，下级的却没清掉；
        for (Comment value : commentList) {
            commentService.deleteComment(value.getCommentId());
        }
        commentService.deleteComment(commentId);
        return map;
    }

    @PostMapping("/updateIsPass")
    public Map<String, String> updateIsPass(int isPass, String commentId) {
        Map<String, String> map = new HashMap<>();
        commentService.updateIsPass(isPass, commentId);
        Comment comment = commentService.selectCommentByCommentId(commentId);
        Commodity commodity = commodityService.selectOneByCommodityId(comment.getCommodityId());
        if (isPass == 1) {
            map.put("msg", "驳回成功");
        }
        if (isPass == 1) {
            map.put("msg", "驳回成功");
        } else if (isPass == 2) {
            map.put("msg", "Pass该评论");
            if (comment.getUpCommentId().equals(commodity.getCommentAreaId())) {
                //如果相等说明这是在一个商品下的评论
                //此时评论的id为商品评论区的id加上该商品的评论数
                comment.setIsPass(0);
                commodityService.updateReviews(comment.getReviews(), commodity.getCommodityId());
                commodityService.updateNowCommentNum(commodity.getNowCommentNum() + 1, commodity.getCommodityId());
                Date day = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                comment.setPublishTime(sdf.format(day));
            } else {
                //不想等说明这是在一个评论下的评论
                //此时评论的id为上级评论的id加上回复上级评论的个数
                comment.setCommentId(comment.getUpCommentId() + "%" + comment.getResponseNum());
                comment.setIsPass(0);
                //评论回复数加1
                commentService.updateNowResponseNum(comment.getResponseNum() + 1, comment.getCommentId());
                Date day = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                comment.setPublishTime(sdf.format(day));
            }

        }

        return map;
    }

    //这端用来管理员查询所有未审核的评论
    @GetMapping("/selectAllNoPassComment")
    public List<Comment> selectAllNoPassComment() {
        return commentService.selectAllNoPassComment();
    }

    //查询该评论区或者该评论下所有回复
    @PostMapping("/selectAllPassCommentByUpCommentId")
    public List<Comment> selectAllPassCommentByUpCommentId(String upCommentId) {
        return commentService.selectAllPassCommentByUpCommentId(upCommentId);
    }

    //查找自己所有发布的评论
    @PostMapping("/selectAllSelfCommentByPublisherId")
    public List<Comment> selectAllSelfCommentByPublisherId(String publisherId) {
        return commentService.selectAllSelfCommentByPublisherId(publisherId);
    }

}
