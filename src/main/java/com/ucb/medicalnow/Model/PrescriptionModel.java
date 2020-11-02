package com.ucb.medicalnow.Model;

import java.sql.Date;

public class PrescriptionModel {

    private Integer prescription_id;
    private String diagnosis;
    private String doctorFirstName;
    private String doctorFirstSurname;
    private Date prescriptionDate;

    public PrescriptionModel() {
    }

    public PrescriptionModel(Integer prescription_id, String diagnosis, String doctorFirstName, String doctorFirstSurname, Date prescriptionDate) {
        this.prescription_id = prescription_id;
        this.diagnosis = diagnosis;
        this.doctorFirstName = doctorFirstName;
        this.doctorFirstSurname = doctorFirstSurname;
        this.prescriptionDate = prescriptionDate;
    }

    public Integer getPrescription_id() {
        return prescription_id;
    }

    public void setPrescription_id(Integer prescription_id) {
        this.prescription_id = prescription_id;
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
                "prescription_id=" + prescription_id +
                ", diagnosis='" + diagnosis + '\'' +
                ", doctorFirstName='" + doctorFirstName + '\'' +
                ", doctorFirstSurname='" + doctorFirstSurname + '\'' +
                ", prescriptionDate=" + prescriptionDate +
                '}';
    }
}
