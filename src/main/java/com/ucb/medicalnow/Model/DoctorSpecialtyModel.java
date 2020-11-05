package com.ucb.medicalnow.Model;

public class DoctorSpecialtyModel {

    private char firstLetter;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Double qualification;
    private Integer doctorId;

    public DoctorSpecialtyModel() {
    }

    public DoctorSpecialtyModel(Integer doctorId, char firstLetter, String firstName, String firstSurname, String secondSurname, Double qualification, String specialtyName) {
        this.doctorId = doctorId;
        this.firstLetter = firstLetter;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.qualification = qualification;
        this.specialtyName = specialtyName;
    }

    public DoctorSpecialtyModel(String firstName, String firstSurname, String secondSurname, String specialtyName, Double qualification, Integer doctorId) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.qualification = qualification;
        this.doctorId = doctorId;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }
}
