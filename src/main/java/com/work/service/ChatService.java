package com.work.service;

import com.work.pojo.ChatMessage;
import com.work.pojo.ResultInfo;

public interface ChatService {

    /**
     * 查询聊天双方的关联id
     * @param fromUser
     * @param toUser
     * @return
     */
    String selectAssociation(String fromUser, String toUser);

    /**
     * 是否第一次聊天
     * @param fromUser
     * @param toUser
     * @return
     */
    boolean isFirstChat(String fromUser, String toUser);

    /**
     * 保存聊天记录
     * @param chatMessage
     * @return
     */
    void saveMessage(ChatMessage chatMessage);

    /**
     * 获取当前用户的聊天列表
     * @param fromUser
     * @return
     */
    ResultInfo getFromUserChatList(String fromUser);

    /**
     * 获取发送者与接收者的最近的聊天记录
     * @param fromUser
     * @param toUser
     * @param startIndex
     * @param pageSize
     * @return
     */
    ResultInfo getRecentChatRecords(String fromUser, String toUser, int startIndex, int pageSize);

    /**
     * 获取发送者与接收者聊天记录的总页数
     * @param linkId
     * @return
     */
    ResultInfo getPageNumber(String linkId);

    /**
     * 更新是否在同一窗口值
     * @param fromUser
     * @param toUser
     */
    void updateWindows(String fromUser, String toUser);


    /**
     * 获取当前用户的未读数
     * @param username
     * @return
     */
    ResultInfo getUnreadTotalNumber(String username);

    /**
     * 重置窗口值
     * @param username
     */
    void resetWindows(String username);



}
