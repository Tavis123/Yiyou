package com.work.mapper;

import com.work.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChatMapper {

    /**
     * 查找两个用户是否第一次聊天
     *
     * @param fromUser 发送者
     * @param toUser   接收者
     * @return 如果是 返回linkId
     */
    String selectAssociation(@Param("fromUser") String fromUser, @Param("toUser") String toUser);

    /**
     * 添加两用户之间的关联（第一次聊天）
     *
     * @param chatLink 关联对象
     * @return
     */
    void addAssociation(ChatLink chatLink);

    /**
     * 添加两条聊天列表记录 两条记录包括：发送者、接收者（第一次聊天）
     *
     * @param chatLists
     */
    void addChatListRecords(@Param("chatLists") ArrayList<ChatList> chatLists);

    /**
     * 根据发送者（用户名）查询自己的聊天列表
     *
     * @param fromUser 发送者
     * @return
     */
    List<ChatListData> selectChatLists(String fromUser);

    /**
     * 根据linkId查询两者之间的聊天记录
     *
     * @param linkId
     * @param startIndex
     * @param messageSize
     * @return
     */
    List<ChatMessageData> selectChatMessages(@Param("linkId") String linkId, @Param("startIndex") int startIndex, @Param("messageSize") int messageSize);


    /**
     * 获取指定linkId的聊天信息总数
     *
     * @param linkId
     * @return
     */
    int selectChatMessagesTotalNumber(String linkId);

    /**
     * 添加聊天信息记录
     *
     * @param message
     */

    void addChatMessage(ChatMessage message);


    /**
     * 查询聊天双方是否在同一窗口
     *
     * @param linkId
     * @param fromUser
     * @param toUser
     * @return
     */
    int selectIsSaveWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser, @Param("toUser") String toUser);

    /**
     * 更新是否在同一窗口的值
     *
     * @param linkId
     * @param fromUser
     */
    void updateIsSaveWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser);

    /**
     * 更新除了linkId的数据的窗口值 -1
     *
     * @param linkId
     * @param fromUser
     */
    void updateOtherWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser);


    /**
     * 更新未读数
     *
     * @param fromUser
     * @param toUser
     * @param newUnread
     * @param linkId
     */
    void updateUnread(@Param("fromUser") String fromUser, @Param("toUser") String toUser, @Param("newUnread") int newUnread, @Param("linkId") String linkId);


    /**
     * 清空未读数
     *
     * @param fromUser
     * @param toUser
     * @param linkId
     */
    void clearUnread(@Param("fromUser") String fromUser, @Param("toUser") String toUser, @Param("linkId") String linkId);

    /**
     * 更新信息的状态
     *
     * @param linkId
     */
    void updateMessageStatus(String linkId);

    /**
     * 获取用户的所有未读数
     *
     * @param username
     * @return
     */
    int selectUnreadTotalNumber(String username);

    /**
     * 将窗口值重置为0
     *
     * @param username
     */
    void resetWindows(String username);


}
