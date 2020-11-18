package com.ucb.medicalnow.Model;

import java.sql.Date;

public class ConsultModel {

    private Integer consultId;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Date startDate;

    public ConsultModel() {
    }

    public ConsultModel(Integer consultId, String firstName, String firstSurname, String secondSurname, String specialtyName, Date startDate) {
        this.consultId = consultId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.startDate = startDate;
    }

    public ConsultModel(Integer consultId, String firstName, String firstSurname, String secondSurname, Date startDate) {
        this.consultId = consultId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.startDate = startDate;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "ConsultsPatientModel{" +
                "consultId=" + consultId +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", startDate=" + startDate +
                '}';
    }
}
