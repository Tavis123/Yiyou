package com.work.service.impl;

import com.work.pojo.Comment;
import com.work.mapper.CommentMapper;
import com.work.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int addCommment(Comment comment) {
        return commentMapper.addCommment(comment);
    }

    @Override
    public int updateIsPass(int isPass, String commentId) {
        return commentMapper.updateIsPass(isPass, commentId);
    }

    @Override
    public List<Comment> selectAllNoPassComment() {
        return commentMapper.selectAllNoPassComment();
    }

    @Override
    public List<Comment> selectAllPassCommentByUpCommentId(String upCommentId) {
        return commentMapper.selectAllPassCommentByUpCommentId(upCommentId);
    }

    @Override
    public List<Comment> selectAllSelfCommentByPublisherId(String publisherId) {
        return commentMapper.selectAllSelfCommentByPublisherId(publisherId);
    }

    @Override
    public int updateResponseNum(int responseNum, String commentId) {
        return commentMapper.updateResponseNum(responseNum, commentId);
    }

    @Override
    public Comment selectCommentByCommentId(String commentId) {
        return commentMapper.selectCommentByCommentId(commentId);
    }

    @Override
    public int updateNowResponseNum(int nowResponseNum, String commentId) {
        return commentMapper.updateNowResponseNum(nowResponseNum, commentId);
    }

    @Override
    public void deleteComment(String commentId) {
        commentMapper.deleteComment(commentId);
    }
}
