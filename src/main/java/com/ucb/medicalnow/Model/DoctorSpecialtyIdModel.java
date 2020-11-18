package com.ucb.medicalnow.Model;

public class DoctorSpecialtyIdModel {

    private Integer doctorSpecialtyId;

    public DoctorSpecialtyIdModel() {
    }

    public DoctorSpecialtyIdModel(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    public Integer getDoctorSpecialtyId() {
        return doctorSpecialtyId;
    }

    public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    @Override
    public String toString() {
        return "DoctorSpecialtyIdModel{" +
                "doctorSpecialtyId=" + doctorSpecialtyId +
                '}';
    }
}
