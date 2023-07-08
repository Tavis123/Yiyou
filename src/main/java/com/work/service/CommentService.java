package com.work.service;

import com.work.pojo.Comment;

import java.util.List;

public interface CommentService {
    int addCommment(Comment comment);

    int updateIsPass(int isPass, String commentId);

    List<Comment> selectAllNoPassComment();

    List<Comment> selectAllPassCommentByUpCommentId(String upCommentId);

    List<Comment> selectAllSelfCommentByPublisherId(String publisherId);

    int updateResponseNum(int responseNum, String commentId);

    Comment selectCommentByCommentId(String commentId);

    int updateNowResponseNum(int nowResponseNum, String commentId);

    void deleteComment(String commentId);
}
