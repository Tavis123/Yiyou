package com.work.pojo;


public class ChatList {

    //�б�id��������
    private int listId;

    //��ϵ������
    private String linkId;

    //������
    private String fromUser;

    //������
    private String toUser;

    //�����ߵ�ͼƬ
    private String toUserPicture;

    //�������Ƿ��ڴ���
    private Boolean fromWindow;

    //�������Ƿ��ڴ���
    private Boolean toWindow;

    //δ���� fromUser��δ����
    private int unread;

    //�Ƿ�ɾ��
    private Boolean status;

    public ChatList(String linkId, String fromUser, String toUser, String toUserPicture, Boolean fromWindow, Boolean toWindow, int unread) {
        this.linkId = linkId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.toUserPicture = toUserPicture;
        this.fromWindow = fromWindow;
        this.toWindow = toWindow;
        this.unread = unread;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToUserPicture() {
        return toUserPicture;
    }

    public void setToUserPicture(String toUserPicture) {
        this.toUserPicture = toUserPicture;
    }

    public Boolean getFromWindow() {
        return fromWindow;
    }

    public void setFromWindow(Boolean fromWindow) {
        this.fromWindow = fromWindow;
    }

    public Boolean getToWindow() {
        return toWindow;
    }

    public void setToWindow(Boolean toWindow) {
        this.toWindow = toWindow;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ChatList{" +
                "listId=" + listId +
                ", linkId='" + linkId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", fromWindow=" + fromWindow +
                ", toWindow=" + toWindow +
                ", unread=" + unread +
                ", status=" + status +
                '}';
    }
}

