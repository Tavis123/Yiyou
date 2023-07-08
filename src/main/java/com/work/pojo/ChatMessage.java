package com.work.pojo;

import java.util.Date;

public class ChatMessage {

    //�ı�
    public static final int MESSAGE_TYPE_TEXT = 0;

    //ͼƬ
    public static final int MESSAGE_TYPE_IMAGE = 1;

    //��Ϣid��������
    private int messageId;

    //��ϵ��id
    private String linkId;

    //������
    private String fromUser;

    //������
    private String toUser;

    //����
    private String content;

    //����ʱ��
    private Date sendTime;

    //��Ϣ����  0--��ͨ�ı���Ĭ�ϣ�
    private int type = MESSAGE_TYPE_TEXT;

    //�Ƿ�Ϊ���һ��
    private Boolean isLatest;

    public ChatMessage() {
    }

    public ChatMessage(String linkId, String fromUser, String toUser, String content, Date sendTime, Boolean isLatest) {
        this.linkId = linkId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.sendTime = sendTime;
        this.isLatest = isLatest;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Boolean getLatest() {
        return isLatest;
    }

    public void setLatest(Boolean latest) {
        isLatest = latest;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId=" + messageId +
                ", linkId='" + linkId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                ", type=" + type +
                ", isLatest=" + isLatest +
                '}';
    }
}
