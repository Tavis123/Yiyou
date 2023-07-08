package com.work.pojo;

import java.util.Date;

public class ChatMessage {

    //文本
    public static final int MESSAGE_TYPE_TEXT = 0;

    //图片
    public static final int MESSAGE_TYPE_IMAGE = 1;


    //信息id（自增）
    private int messageId;

    //关系表id
    private String linkId;

    //发送者
    private String fromUser;

    //接收者
    private String toUser;

    //内容
    private String content;

    //发送时间
    private Date sendTime;

    //消息类型  0--普通文本（默认）
    private int type = MESSAGE_TYPE_TEXT;

    //是否为最后一条
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
