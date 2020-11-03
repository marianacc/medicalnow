package com.ucb.medicalnow.Model;

import java.sql.Date;

public class PrescriptionModel {

    private Integer prescriptionId;
    private String diagnosis;
    private String doctorFirstName;
    private String doctorFirstSurname;
    private Date prescriptionDate;

    public PrescriptionModel() {
    }

    public PrescriptionModel(Integer prescriptionId, String diagnosis, String doctorFirstName, String doctorFirstSurname, Date prescriptionDate) {
        this.prescriptionId = prescriptionId;
        this.diagnosis = diagnosis;
        this.doctorFirstName = doctorFirstName;
        this.doctorFirstSurname = doctorFirstSurname;
        this.prescriptionDate = prescriptionDate;
    }

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorFirstSurname() {
        return doctorFirstSurname;
    }

    public void setDoctorFirstSurname(String doctorFirstSurname) {
        this.doctorFirstSurname = doctorFirstSurname;
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
                ", diagnosis='" + diagnosis + '\'' +
                ", doctorFirstName='" + doctorFirstName + '\'' +
                ", doctorFirstSurname='" + doctorFirstSurname + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                '}';
    }
}
