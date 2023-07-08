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

        //判断两者是否第一次聊天,返回两者的linkId
        String linkId = chatMapper.selectAssociation(fromUser, toUser);

        if (linkId == null) {

            String newLinkId = UUID.randomUUID().toString();
            //添加两者的关系表数据
            ChatLink chatLink = new ChatLink(newLinkId, fromUser, toUser, new Date());
            //返回插入数据的id主键
            chatMapper.addAssociation(chatLink);

            //查询用户头像
            String fromUserPicture = userMapper.selectAvatarByUsername(fromUser);
            String toUserPicture = userMapper.selectAvatarByUsername(toUser);


            //添加两条聊天列表数据（发送方，接收方）
            ChatList fromUserList = new ChatList(newLinkId, fromUser, toUser, toUserPicture, false, false, 0);
            ChatList toUserList = new ChatList(newLinkId, toUser, fromUser, fromUserPicture, false, false, 0);

            ArrayList<ChatList> chatLists = new ArrayList<ChatList>();
            chatLists.add(fromUserList);
            chatLists.add(toUserList);

            chatMapper.addChatListRecords(chatLists);

            //插入一条空白消息（为了更好地联表查询数据）
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

        //将一条的信息的状态（最后一条）改为否
        chatMapper.updateMessageStatus(linkId);

        //判断聊天双方是否在同一窗口聊天
        int flag = chatMapper.selectIsSaveWindows(linkId, fromUser, toUser);

        // 1--只有一方在窗口中 未读数加给接收方，2--两者都在窗口中 清除未读数
        if (flag == 1) {
            //更新未读数
            chatMapper.updateUnread(fromUser, toUser, 1, linkId);
        } else if (flag == 2) {
            //清空所有的未读数
            chatMapper.clearUnread(fromUser, toUser, linkId);
        }

        //保存聊天记录
        chatMapper.addChatMessage(chatMessage);


    }

    @Override
    public ResultInfo getFromUserChatList(String fromUser) {

        //查询获取用户的聊天列表
        List<ChatListData> chatListData = chatMapper.selectChatLists(fromUser);

        return ResultInfo.successInfo("聊天列表", chatListData);
    }

    @Override
    public ResultInfo getRecentChatRecords(String fromUser, String toUser, int startIndex, int pageSize) {

        //获取两者之间的关联id
        String linkId = chatMapper.selectAssociation(fromUser, toUser);
        System.out.println(fromUser + "    " + toUser);
        System.out.println(linkId);
        //查询最近的六条聊天信息（包括未读）
        List<ChatMessageData> chatMessageData = chatMapper.selectChatMessages(linkId, startIndex, pageSize);

        //反转list
        Collections.reverse(chatMessageData);

        //清空未读信息
        chatMapper.clearUnread(fromUser, toUser, linkId);

        return ResultInfo.successInfo("最近的六条聊天记录", chatMessageData);
    }

    @Override
    public ResultInfo getPageNumber(String linkId) {

        int MessagesTotalNumber = chatMapper.selectChatMessagesTotalNumber(linkId);

        int pageNumber = (MessagesTotalNumber % MESSAGE_SIZE == 0) ? (MessagesTotalNumber / MESSAGE_SIZE) : (MessagesTotalNumber / MESSAGE_SIZE) + 1;

        return ResultInfo.successInfo("总页数", pageNumber);
    }

    @Override
    public void updateWindows(String fromUser, String toUser) {

        //获取两者之间的关联id
        String linkId = chatMapper.selectAssociation(fromUser, toUser);

        //更新点击了聊天框的同一窗口值
        chatMapper.updateIsSaveWindows(linkId, fromUser);

        //清除当前fromUser的未读数
        chatMapper.clearUnread(fromUser, toUser, linkId);

        //更新其他窗口的值
        chatMapper.updateOtherWindows(linkId, fromUser);
    }

    @Override
    public ResultInfo getUnreadTotalNumber(String username) {

        //查询用户的所有的未读数
        Integer unreadTotalNumber = chatMapper.selectUnreadTotalNumber(username);

        if (unreadTotalNumber != null) {
            return ResultInfo.successInfo("总未读数", unreadTotalNumber);
        } else {
            return ResultInfo.successInfo("总未读数", 0);
        }
    }

    @Override
    public void resetWindows(String username) {

        //退出websocket连接时，重置窗口值
        chatMapper.resetWindows(username);
    }
}
