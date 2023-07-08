package com.work.mapper;

import com.work.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ChatMapper {

    /**
     * ���������û��Ƿ��һ������
     *
     * @param fromUser ������
     * @param toUser   ������
     * @return ����� ����linkId
     */
    String selectAssociation(@Param("fromUser") String fromUser, @Param("toUser") String toUser);

    /**
     * ������û�֮��Ĺ�������һ�����죩
     *
     * @param chatLink ��������
     * @return
     */
    void addAssociation(ChatLink chatLink);

    /**
     * ������������б��¼ ������¼�����������ߡ������ߣ���һ�����죩
     *
     * @param chatLists
     */
    void addChatListRecords(@Param("chatLists") ArrayList<ChatList> chatLists);

    /**
     * ���ݷ����ߣ��û�������ѯ�Լ��������б�
     *
     * @param fromUser ������
     * @return
     */
    List<ChatListData> selectChatLists(String fromUser);

    /**
     * ����linkId��ѯ����֮��������¼
     *
     * @param linkId
     * @param startIndex
     * @param messageSize
     * @return
     */
    List<ChatMessageData> selectChatMessages(@Param("linkId") String linkId, @Param("startIndex") int startIndex, @Param("messageSize") int messageSize);


    /**
     * ��ȡָ��linkId��������Ϣ����
     *
     * @param linkId
     * @return
     */
    int selectChatMessagesTotalNumber(String linkId);

    /**
     * ���������Ϣ��¼
     *
     * @param message
     */

    void addChatMessage(ChatMessage message);


    /**
     * ��ѯ����˫���Ƿ���ͬһ����
     *
     * @param linkId
     * @param fromUser
     * @param toUser
     * @return
     */
    int selectIsSaveWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser, @Param("toUser") String toUser);

    /**
     * �����Ƿ���ͬһ���ڵ�ֵ
     *
     * @param linkId
     * @param fromUser
     */
    void updateIsSaveWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser);

    /**
     * ���³���linkId�����ݵĴ���ֵ -1
     *
     * @param linkId
     * @param fromUser
     */
    void updateOtherWindows(@Param("linkId") String linkId, @Param("fromUser") String fromUser);


    /**
     * ����δ����
     *
     * @param fromUser
     * @param toUser
     * @param newUnread
     * @param linkId
     */
    void updateUnread(@Param("fromUser") String fromUser, @Param("toUser") String toUser, @Param("newUnread") int newUnread, @Param("linkId") String linkId);


    /**
     * ���δ����
     *
     * @param fromUser
     * @param toUser
     * @param linkId
     */
    void clearUnread(@Param("fromUser") String fromUser, @Param("toUser") String toUser, @Param("linkId") String linkId);

    /**
     * ������Ϣ��״̬
     *
     * @param linkId
     */
    void updateMessageStatus(String linkId);

    /**
     * ��ȡ�û�������δ����
     *
     * @param username
     * @return
     */
    int selectUnreadTotalNumber(String username);

    /**
     * ������ֵ����Ϊ0
     *
     * @param username
     */
    void resetWindows(String username);


}
