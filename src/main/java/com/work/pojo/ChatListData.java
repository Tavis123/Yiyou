package com.work.pojo;

import java.io.Serializable;


public class ChatListData implements Serializable {


    //两者的关联id
    private String linkId;

    //聊天对象用户名
    private String friendName;

    //聊天对象的头像
    private String friendPicture;

    //最后一条信息
    private String lastMessage;

    //未读数
    private int unread;

    //最后一条信息发送时间
    private String sendTime;

    //发送者在哪个聊天框
    private int fromWindow;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendPicture() {
        return friendPicture;
    }

    public void setFriendPicture(String friendPicture) {
        this.friendPicture = friendPicture;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public int getFromWindow() {
        return fromWindow;
    }

    public void setFromWindow(int fromWindow) {
        this.fromWindow = fromWindow;
    }

    @Override
    public String toString() {
        return "ChatListData{" +
                "linkId='" + linkId + '\'' +
                ", friendName='" + friendName + '\'' +
                ", friendPicture='" + friendPicture + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", unread=" + unread +
                ", sendTime='" + sendTime + '\'' +
                ", fromWindow=" + fromWindow +
                '}';
    }
}
