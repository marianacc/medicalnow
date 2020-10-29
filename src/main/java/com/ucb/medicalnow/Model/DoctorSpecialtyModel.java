package com.ucb.medicalnow.Model;

public class DoctorSpecialtyModel {

    private Integer doctorId;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private Double qualification;

    public DoctorSpecialtyModel() {
    }

    public DoctorSpecialtyModel(Integer doctorId, String firstName, String firstSurname, String secondSurname, Double qualification) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.qualification = qualification;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "DoctorSpecialtyModel{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", qualification=" + qualification +
                '}';
    }
}
