package com.ucb.medicalnow.Model;

public class DoctorSpecialtyNameModel {

    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;

    public DoctorSpecialtyNameModel() {
    }

    public DoctorSpecialtyNameModel(String firstName, String firstSurname, String secondSurname, String specialtyName) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
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

    @Override
    public String toString() {
        return "DoctorSpecialtyNameModel{" +
                "firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                '}';
    }
}
