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
            //������˵��������һ����Ʒ�µ�����
            //��ʱ���۵�idΪ��Ʒ��������id���ϸ���Ʒ��������
            comment.setCommentId(comment.getUpCommentId() + "%" + commodity.getCommentNum());
            commodityService.updateCommentNum(commodity.getCommentNum() + 1, commodity.getCommodityId());
            comment.setIsPass(0);
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setPublishTime(sdf.format(day));
        } else {
            //�����˵��������һ�������µ�����
            //��ʱ���۵�idΪ�ϼ����۵�id���ϻظ��ϼ����۵ĸ���
            comment.setCommentId(comment.getUpCommentId() + "%" + comment.getResponseNum());
            comment.setIsPass(0);
            //���ۻظ�����1
            commentService.updateResponseNum(comment.getResponseNum() + 1, comment.getCommentId());
            Date day = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setPublishTime(sdf.format(day));
        }
        comment.setIsPass(0);
        comment.setNowResponseNum(0);
        comment.setLikeCount(0);
        commentService.addCommment(comment);
        map.put("msg", "���۳ɹ���");
        return map;

    }

    @DeleteMapping("/deleteComment")
    public Map<String, String> deleteComment(String commentId) {
        //��ᵼ��ɾ�������۵�����û��ɾȥ���������޸�
        Map<String, String> map = new HashMap<>();
        map.put("msg", "ɾ���ɹ�!");
        List<Comment> commentList = commentService.selectAllPassCommentByUpCommentId(commentId);
        Comment comment = commentService.selectCommentByCommentId(commentId);
        Commodity commodity = commodityService.selectOneByCommodityId(comment.getCommodityId());
        if (comment.getUpCommentId().equals(commodity.getCommentAreaId())) {
            //˵�������������ĳ��Ʒ������������ʱҪɾ�������۾ͱ�����ٸ���Ʒ�ĵ�ǰ������
            commodityService.updateNowCommentNum(commodity.getCommentNum() - 1, commodity.getCommodityId());
        } else {
            //˵������������ĳ�������µ����ۣ���ʱҪ���ٵ����ϼ����۵ĵ�ǰ�ظ���
            Comment upComment = commentService.selectCommentByCommentId(comment.getUpCommentId());
            commentService.updateResponseNum(upComment.getNowResponseNum() - 1, upComment.getUpCommentId());
        }
        //��λ���bug����ѱ�������գ��¼���ȴû�����
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
            map.put("msg", "���سɹ�");
        }
        if (isPass == 1) {
            map.put("msg", "���سɹ�");
        } else if (isPass == 2) {
            map.put("msg", "Pass������");
            if (comment.getUpCommentId().equals(commodity.getCommentAreaId())) {
                //������˵��������һ����Ʒ�µ�����
                //��ʱ���۵�idΪ��Ʒ��������id���ϸ���Ʒ��������
                comment.setIsPass(0);
                commodityService.updateReviews(comment.getReviews(), commodity.getCommodityId());
                commodityService.updateNowCommentNum(commodity.getNowCommentNum() + 1, commodity.getCommodityId());
                Date day = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                comment.setPublishTime(sdf.format(day));
            } else {
                //�����˵��������һ�������µ�����
                //��ʱ���۵�idΪ�ϼ����۵�id���ϻظ��ϼ����۵ĸ���
                comment.setCommentId(comment.getUpCommentId() + "%" + comment.getResponseNum());
                comment.setIsPass(0);
                //���ۻظ�����1
                commentService.updateNowResponseNum(comment.getResponseNum() + 1, comment.getCommentId());
                Date day = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                comment.setPublishTime(sdf.format(day));
            }

        }

        return map;
    }

    //�����������Ա��ѯ����δ��˵�����
    @GetMapping("/selectAllNoPassComment")
    public List<Comment> selectAllNoPassComment() {
        return commentService.selectAllNoPassComment();
    }

    //��ѯ�����������߸����������лظ�
    @PostMapping("/selectAllPassCommentByUpCommentId")
    public List<Comment> selectAllPassCommentByUpCommentId(String upCommentId) {
        return commentService.selectAllPassCommentByUpCommentId(upCommentId);
    }

    //�����Լ����з���������
    @PostMapping("/selectAllSelfCommentByPublisherId")
    public List<Comment> selectAllSelfCommentByPublisherId(String publisherId) {
        return commentService.selectAllSelfCommentByPublisherId(publisherId);
    }

}
