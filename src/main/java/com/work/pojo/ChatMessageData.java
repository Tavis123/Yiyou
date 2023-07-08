package com.work.pojo;


public class ChatMessageData {

    //������
    private String sendUser;

    //��Ϣ����
    private String content;

    //����ʱ��
    private String sendTime;


    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "ChatMessageData{" +
                "sendUser='" + sendUser + '\'' +
                ", content='" + content + '\'' +
                ", sendTime='" + sendTime + '\'' +
                '}';
    }
}
