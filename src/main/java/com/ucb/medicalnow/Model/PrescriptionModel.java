package com.ucb.medicalnow.Model;

import java.sql.Date;

public class PrescriptionModel {

    private Integer prescriptionId;
    private Integer medicalHistoryId;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Date prescriptionDate;

    public PrescriptionModel() {
    }

    public PrescriptionModel(Integer prescriptionId, Integer medicalHistoryId, String firstName, String firstSurname, String secondSurname, String specialtyName, Date prescriptionDate) {
        this.prescriptionId = prescriptionId;
        this.medicalHistoryId = medicalHistoryId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.prescriptionDate = prescriptionDate;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "prescriptionId=" + prescriptionId +
                ", medicalHistoryId=" + medicalHistoryId +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                '}';
    }
}
