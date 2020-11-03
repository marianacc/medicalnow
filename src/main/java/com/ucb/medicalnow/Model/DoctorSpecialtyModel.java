package com.ucb.medicalnow.Model;

public class DoctorSpecialtyModel {

    private String specialtyName;
    private char firstLetter;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private Double qualification;
    private Integer doctorId;

    public DoctorSpecialtyModel() {
    }

    public DoctorSpecialtyModel(String specialtyName, char firstLetter, String firstName, String firstSurname, String secondSurname, Double qualification, Integer doctorId) {
        this.specialtyName = specialtyName;
        this.firstLetter = firstLetter;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.qualification = qualification;
        this.doctorId = doctorId;
    }

    public DoctorSpecialtyModel(String specialtyName, String firstName, String firstSurname, String secondSurname, Double qualification, Integer doctorId) {
        this.specialtyName = specialtyName;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.qualification = qualification;
        this.doctorId = doctorId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
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

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "DoctorSpecialtyModel{" +
                "specialtyName='" + specialtyName + '\'' +
                ", firstLetter=" + firstLetter +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", qualification=" + qualification +
                ", doctorId=" + doctorId +
                '}';
    }
}
