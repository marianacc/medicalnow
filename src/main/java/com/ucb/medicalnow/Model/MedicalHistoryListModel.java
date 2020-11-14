package com.ucb.medicalnow.Model;

import java.sql.Date;

public class MedicalHistoryListModel {

    private Integer medicalHistoryId;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Date startDate;
    private Integer status;

    public MedicalHistoryListModel() {
    }

    public MedicalHistoryListModel(Integer medicalHistoryId, String firstName, String firstSurname, String secondSurname, String specialtyName, Date startDate, Integer status) {
        this.medicalHistoryId = medicalHistoryId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.startDate = startDate;
        this.status = status;
    }

    public Integer getMedicalHistoryId() {
        return medicalHistoryId;
    }

    public void setMedicalHistoryId(Integer medicalHistoryId) {
        this.medicalHistoryId = medicalHistoryId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MedicalHistoryListModel{" +
                "medicalHistoryId=" + medicalHistoryId +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", startDate=" + startDate +
                ", status=" + status +
                '}';
    }
}
