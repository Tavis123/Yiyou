package com.work.pojo;


public class ResultInfo {

    /**
     * ������״̬�룺
     * �ɹ�Ϊ1��ʧ��Ϊ0���ɹ�������Ϊ3
     */
    private Integer status;

    /**
     * ���صĽ����
     */
    private Object data;

    /**
     * ���ص������Ϣ
     */
    private String message;


    public static ResultInfo successInfo(){
        return successInfo(null, null);
    }

    public static ResultInfo successInfo(String message){
        return successInfo(message, null);
    }

    public static ResultInfo successInfo(String message, Object data){
        return new ResultInfo(1, data, message);
    }

    public static ResultInfo failInfo(){
        return failInfo(null, null);
    }

    public static ResultInfo failInfo(String message){
        return failInfo(message, null);
    }

    public static ResultInfo failInfo(String message, Object data){
        return new ResultInfo(0, data, message);
    }

    public static ResultInfo warnInfo(String message){
        return new ResultInfo(2, null, message);
    }


    public ResultInfo(Integer status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "status=" + status +
                ", data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

}
