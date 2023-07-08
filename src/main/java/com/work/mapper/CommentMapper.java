package com.work.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import com.work.pojo.Comment;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Insert("insert into comment(publisherId, commodityId, commentId, publishTime, likeCount, upCommentId, reviews,detailResponse, responseNum, isPass, nowResponseNum) values (#{publisherId}, #{commodityId}, #{commentId}, #{publishTime}, #{likeCount}, #{upCommentId}, #{reviews}, #{detailResponse}, #{responseNum}, #{isPass}, #{nowResponseNum}")
    int addCommment(Comment comment);

    @Update("update comment set isPass = #{isPass} where commentId = #{commentId}")
    int updateIsPass(int isPass, String commentId);

    @Select("select * from comment where isPass = 0")
    List<Comment> selectAllNoPassComment();

    @Select("select * from comment where isPass = 2")
    List<Comment> selectAllPassCommentByUpCommentId(String upCommentId);

    @Select("select * from comment where publisherId = #{publisherId}")
    List<Comment> selectAllSelfCommentByPublisherId(String publisherId);

    @Update("update comment set responseNum = #{responseNum} where commentId = #{commentId}")
    int updateResponseNum(int responseNum, String commentId);

    @Select("select * from comment where commentId = #{commentId} and isPass = 2")
    Comment selectCommentByCommentId(String commentId);

    @Update("update comment set nowResponseNum = #{nowResponseNum} where commentId = #{commentId}")
    int updateNowResponseNum(int nowResponseNum, String commentId);

    @Delete("delete from comment where commentId = #{commentId}")
    int deleteComment(String commentId);
}
