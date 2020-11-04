package com.ucb.medicalnow.Model;

import java.util.Arrays;

public class ConsultModel {

    private Integer userId;
    private Integer doctorId;
    private String message;
    private byte[] resource;

    public ConsultModel() {
    }

    public ConsultModel(Integer userId, Integer doctorId, String message, byte[] resource) {
        this.userId = userId;
        this.doctorId = doctorId;
        this.message = message;
        this.resource = resource;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getResource() {
        return resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "ConsultModel{" +
                "userId=" + userId +
                ", doctorId=" + doctorId +
                ", message='" + message + '\'' +
                ", resource=" + Arrays.toString(resource) +
                '}';
    }
}
