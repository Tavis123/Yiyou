package com.work.pojo;

import java.util.Date;

public class ChatLink {

    //��ϵ��id
    private String linkId;

    //������
    private String fromUser;

    //������
    private String toUser;

    //����ʱ��
    private Date createTime;

    public ChatLink(String linkId, String fromUser, String toUser, Date createTime) {
        this.linkId = linkId;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createTime = createTime;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChatLink{" +
                "linkId='" + linkId + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
