package com.work.service.impl;

import com.work.mapper.ChatMapper;
import com.work.mapper.UserMapper;
import com.work.pojo.*;
import com.work.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

    private static int MESSAGE_SIZE = 6;

    @Autowired
    private ChatMapper chatMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public String selectAssociation(String fromUser, String toUser) {
        return chatMapper.selectAssociation(fromUser, toUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isFirstChat(String fromUser, String toUser) {

        //�ж������Ƿ��һ������,�������ߵ�linkId
        String linkId = chatMapper.selectAssociation(fromUser, toUser);

        if (linkId == null) {

            String newLinkId = UUID.randomUUID().toString();
            //������ߵĹ�ϵ������
            ChatLink chatLink = new ChatLink(newLinkId, fromUser, toUser, new Date());
            //���ز������ݵ�id����
            chatMapper.addAssociation(chatLink);

            //��ѯ�û�ͷ��
            String fromUserPicture = userMapper.selectAvatarByUsername(fromUser);
            String toUserPicture = userMapper.selectAvatarByUsername(toUser);


            //������������б����ݣ����ͷ������շ���
            ChatList fromUserList = new ChatList(newLinkId, fromUser, toUser, toUserPicture, false, false, 0);
            ChatList toUserList = new ChatList(newLinkId, toUser, fromUser, fromUserPicture, false, false, 0);

            ArrayList<ChatList> chatLists = new ArrayList<ChatList>();
            chatLists.add(fromUserList);
            chatLists.add(toUserList);

            chatMapper.addChatListRecords(chatLists);

            //����һ���հ���Ϣ��Ϊ�˸��õ������ѯ���ݣ�
            ChatMessage chatMessage = new ChatMessage(newLinkId, fromUser, toUser, "", new Date(), true);
            chatMapper.addChatMessage(chatMessage);

            return true;

        }

        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveMessage(ChatMessage chatMessage) {

        String linkId = chatMessage.getLinkId();
        String fromUser = chatMessage.getFromUser();
        String toUser = chatMessage.getToUser();

        //��һ������Ϣ��״̬�����һ������Ϊ��
        chatMapper.updateMessageStatus(linkId);

        //�ж�����˫���Ƿ���ͬһ��������
        int flag = chatMapper.selectIsSaveWindows(linkId, fromUser, toUser);

        // 1--ֻ��һ���ڴ����� δ�����Ӹ����շ���2--���߶��ڴ����� ���δ����
        if (flag == 1) {
            //����δ����
            chatMapper.updateUnread(fromUser, toUser, 1, linkId);
        } else if (flag == 2) {
            //������е�δ����
            chatMapper.clearUnread(fromUser, toUser, linkId);
        }

        //���������¼
        chatMapper.addChatMessage(chatMessage);


    }

    @Override
    public ResultInfo getFromUserChatList(String fromUser) {

        //��ѯ��ȡ�û��������б�
        List<ChatListData> chatListData = chatMapper.selectChatLists(fromUser);

        return ResultInfo.successInfo("�����б�", chatListData);
    }

    @Override
    public ResultInfo getRecentChatRecords(String fromUser, String toUser, int startIndex, int pageSize) {

        //��ȡ����֮��Ĺ���id
        String linkId = chatMapper.selectAssociation(fromUser, toUser);
        System.out.println(fromUser + "    " + toUser);
        System.out.println(linkId);
        //��ѯ���������������Ϣ������δ����
        List<ChatMessageData> chatMessageData = chatMapper.selectChatMessages(linkId, startIndex, pageSize);

        //��תlist
        Collections.reverse(chatMessageData);

        //���δ����Ϣ
        chatMapper.clearUnread(fromUser, toUser, linkId);

        return ResultInfo.successInfo("��������������¼", chatMessageData);
    }

    @Override
    public ResultInfo getPageNumber(String linkId) {

        int MessagesTotalNumber = chatMapper.selectChatMessagesTotalNumber(linkId);

        int pageNumber = (MessagesTotalNumber % MESSAGE_SIZE == 0) ? (MessagesTotalNumber / MESSAGE_SIZE) : (MessagesTotalNumber / MESSAGE_SIZE) + 1;

        return ResultInfo.successInfo("��ҳ��", pageNumber);
    }

    @Override
    public void updateWindows(String fromUser, String toUser) {

        //��ȡ����֮��Ĺ���id
        String linkId = chatMapper.selectAssociation(fromUser, toUser);

        //���µ����������ͬһ����ֵ
        chatMapper.updateIsSaveWindows(linkId, fromUser);

        //�����ǰfromUser��δ����
        chatMapper.clearUnread(fromUser, toUser, linkId);

        //�����������ڵ�ֵ
        chatMapper.updateOtherWindows(linkId, fromUser);
    }

    @Override
    public ResultInfo getUnreadTotalNumber(String username) {

        //��ѯ�û������е�δ����
        Integer unreadTotalNumber = chatMapper.selectUnreadTotalNumber(username);

        if (unreadTotalNumber != null) {
            return ResultInfo.successInfo("��δ����", unreadTotalNumber);
        } else {
            return ResultInfo.successInfo("��δ����", 0);
        }
    }

    @Override
    public void resetWindows(String username) {

        //�˳�websocket����ʱ�����ô���ֵ
        chatMapper.resetWindows(username);
    }
}
