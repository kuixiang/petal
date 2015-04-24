package com.sunflower.petal.service.support;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiangkui on 14-2-22.
 * 导数状态码
 */
public class ImportStatus {
    private static int  NOT_STARTED;//未开始
    private static int  IMPORTING;//正在导数
    private static int HAVA_ENDED;//已经结束

    public enum Status {
       SUCCESS,
        ERROR,
        UNKWON
    } ;
    private int processCode=NOT_STARTED;//过程码
    private Status result= Status.UNKWON;//是否成功
    private String  message="";//附加内容
    private Map<String,String> attachInfo=new HashMap<String,String>();

    public Map<String, String> getAttachInfo() {
        return attachInfo;
    }

    public void setAttachInfo(Map<String, String> attachInfo) {
        this.attachInfo = attachInfo;
    }

    public ImportStatus(){
        processCode=NOT_STARTED;
    }
    //getter and setter
    public void putAttachInfo(String key,String value){
        attachInfo.put(key,value);
    }
    public String getAttachInfo(String key){
        return attachInfo.get(key);
    }

    @Override
    public String toString() {
        return "ImportStatus{" +
                "processCode=" + processCode +
                ", result=" + result +
                ", message='" + message + '\'' +
                ", attachInfo=" + attachInfo +
                '}';
    }

    public int getProcessCode() {
        return processCode;
    }

    public void setProcessCode(int processCode) {
        this.processCode = processCode;
    }

    public Status getResult() {
        return result;
    }

    public void setResult(Status result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
