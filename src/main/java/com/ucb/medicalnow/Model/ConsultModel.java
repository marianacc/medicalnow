package com.ucb.medicalnow.Model;

import java.sql.Date;
import java.util.Arrays;

public class ConsultModel {

    private Integer doctorSpecialtyId;
    private String message;
    private byte[] image;

    public ConsultModel() {
    }

    public ConsultModel(Integer doctorSpecialtyId, String message, byte[] image) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.message = message;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ConsultModel{" +
                "doctorSpecialtyId=" + doctorSpecialtyId +
                ", message='" + message + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
