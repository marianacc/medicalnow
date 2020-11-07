package com.ucb.medicalnow.Model;

public class DoctorSpecialtyModel {

    private Integer doctorSpecialtyId;
    private char firstLetter;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Double qualification;

    public DoctorSpecialtyModel() {
    }

    public DoctorSpecialtyModel(Integer doctorSpecialtyId, char firstLetter, String firstName, String firstSurname, String secondSurname, String specialtyName, Double qualification) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.firstLetter = firstLetter;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.qualification = qualification;
    }

    public DoctorSpecialtyModel(Integer doctorSpecialtyId, String firstName, String firstSurname, String secondSurname, String specialtyName, Double qualification) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.qualification = qualification;
    }

    public Integer getDoctorSpecialtyId() {
        return doctorSpecialtyId;
    }

    public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
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

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
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
                "doctorSpecialtyId=" + doctorSpecialtyId +
                ", firstLetter=" + firstLetter +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", qualification=" + qualification +
                '}';
    }
}
