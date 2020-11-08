package com.ucb.medicalnow.Model;

import java.sql.Date;
import java.util.Arrays;

public class MessageModel {

    private Integer consultId;
    private Integer doctorSpecialtyId;
    private String message;

    public MessageModel() {
    }

    public MessageModel(Integer consultId, String message) {
        this.consultId = consultId;
        this.message = message;
    }

    public MessageModel(Integer consultId, Integer doctorSpecialtyId, String message) {
        this.consultId = consultId;
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.message = message;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
    }

    public Integer getDoctorSpecialtyId() {
        return doctorSpecialtyId;
    }

    public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "consultId=" + consultId +
                ", doctorSpecialtyId=" + doctorSpecialtyId +
                ", message='" + message + '\'' +
                '}';
    }
}
