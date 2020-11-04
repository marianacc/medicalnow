package com.ucb.medicalnow.Model;

import java.sql.Date;
import java.util.Arrays;

public class PatientConsultModel {

    private Integer doctorId;
    private String message;
    private String consultDate;
    private byte[] resource;

    public PatientConsultModel() {
    }

    public PatientConsultModel(Integer doctorId, String message, String consultDate, byte[] resource) {
        this.doctorId = doctorId;
        this.message = message;
        this.consultDate = consultDate;
        this.resource = resource;
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

    public String getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(String consultDate) {
        this.consultDate = consultDate;
    }

    public byte[] getResource() {
        return resource;
    }

    public void setResource(byte[] resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "PatientConsultModel{" +
                ", doctorId=" + doctorId +
                ", message='" + message + '\'' +
                ", consultDate='" + consultDate + '\'' +
                ", resource=" + Arrays.toString(resource) +
                '}';
    }
}
