package com.work.pojo;

import java.io.Serializable;


public class ChatListData implements Serializable {


    //���ߵĹ���id
    private String linkId;

    //��������û���
    private String friendName;

    //��������ͷ��
    private String friendPicture;

    //���һ����Ϣ
    private String lastMessage;

    //δ����
    private int unread;

    //���һ����Ϣ����ʱ��
    private String sendTime;

    //���������ĸ������
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
