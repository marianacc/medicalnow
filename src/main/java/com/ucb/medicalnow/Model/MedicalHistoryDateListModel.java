package com.ucb.medicalnow.Model;

import java.sql.Date;

public class MedicalHistoryDateListModel {

    private Integer consultId;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Date startDate;
    private Integer status;

    public MedicalHistoryDateListModel() {
    }

    public MedicalHistoryDateListModel(Integer consultId, String firstName, String firstSurname, String secondSurname, String specialtyName, Date startDate, Integer status) {
        this.consultId = consultId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.startDate = startDate;
        this.status = status;
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

    public void setSpecialtyName(String specialtyId) {
        this.specialtyName = specialtyId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MedicalHistoryDateListModel{" +
                "consultId=" + consultId +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyId='" + specialtyName + '\'' +
                ", startDate=" + startDate +
                ", status=" + status +
                '}';
    }
}
