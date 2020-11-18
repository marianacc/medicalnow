package com.ucb.medicalnow.Model;

public class MessageModel {

    private Integer consultId;
    private String message;

    public MessageModel() {
    }

    public MessageModel(Integer consultId, String message) {
        this.consultId = consultId;
        this.message = message;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DoctorMessageModel{" +
                "consultId=" + consultId +
                ", message='" + message + '\'' +
                '}';
    }
}
