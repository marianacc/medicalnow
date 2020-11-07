package com.ucb.medicalnow.Model;

import java.sql.Date;
import java.util.Arrays;

public class ConsultModel {

    private Integer doctorSpecialtyId;
    private String message;

    public ConsultModel() {
    }

    public ConsultModel(Integer doctorSpecialtyId, String message) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.message = message;
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
        return "ConsultModel{" +
                "doctorSpecialtyId=" + doctorSpecialtyId +
                ", message='" + message + '\'' +
                '}';
    }
}
