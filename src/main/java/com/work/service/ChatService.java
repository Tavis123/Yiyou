package com.work.service;

import com.work.pojo.ChatMessage;
import com.work.pojo.ResultInfo;

public interface ChatService {

    /**
     * ��ѯ����˫���Ĺ���id
     * @param fromUser
     * @param toUser
     * @return
     */
    String selectAssociation(String fromUser, String toUser);

    /**
     * �Ƿ��һ������
     * @param fromUser
     * @param toUser
     * @return
     */
    boolean isFirstChat(String fromUser, String toUser);

    /**
     * ���������¼
     * @param chatMessage
     * @return
     */
    void saveMessage(ChatMessage chatMessage);

    /**
     * ��ȡ��ǰ�û��������б�
     * @param fromUser
     * @return
     */
    ResultInfo getFromUserChatList(String fromUser);

    /**
     * ��ȡ������������ߵ�����������¼
     * @param fromUser
     * @param toUser
     * @param startIndex
     * @param pageSize
     * @return
     */
    ResultInfo getRecentChatRecords(String fromUser, String toUser, int startIndex, int pageSize);

    /**
     * ��ȡ������������������¼����ҳ��
     * @param linkId
     * @return
     */
    ResultInfo getPageNumber(String linkId);

    /**
     * �����Ƿ���ͬһ����ֵ
     * @param fromUser
     * @param toUser
     */
    void updateWindows(String fromUser, String toUser);


    /**
     * ��ȡ��ǰ�û���δ����
     * @param username
     * @return
     */
    ResultInfo getUnreadTotalNumber(String username);

    /**
     * ���ô���ֵ
     * @param username
     */
    void resetWindows(String username);



}
