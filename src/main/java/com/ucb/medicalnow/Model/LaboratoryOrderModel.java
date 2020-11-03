package com.ucb.medicalnow.Model;

import java.sql.Date;

public class LaboratoryOrderModel {

    private String laboratoryExamsId;
    private String laboratoryName;
    private String doctorFirstName;
    private String doctorFirstSurname;
    private String specialtyName;
    private Date laboratoryOrderDate;
    private String labExamOrder;

    public LaboratoryOrderModel() {
    }

    public LaboratoryOrderModel(String laboratoryExamsId, String laboratoryName, String doctorFirstName, String doctorFirstSurname, String specialtyName, Date laboratoryOrderDate, String labExamOrder) {
        this.laboratoryExamsId = laboratoryExamsId;
        this.laboratoryName = laboratoryName;
        this.doctorFirstName = doctorFirstName;
        this.doctorFirstSurname = doctorFirstSurname;
        this.specialtyName = specialtyName;
        this.laboratoryOrderDate = laboratoryOrderDate;
        this.labExamOrder = labExamOrder;
    }

    public String getLaboratoryExamsId() {
        return laboratoryExamsId;
    }

    public void setLaboratoryExamsId(String laboratoryExamsId) {
        this.laboratoryExamsId = laboratoryExamsId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
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

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Date getLaboratoryOrderDate() {
        return laboratoryOrderDate;
    }

    public void setLaboratoryOrderDate(Date laboratoryOrderDate) {
        this.laboratoryOrderDate = laboratoryOrderDate;
    }

    public String getLabExamOrder() {
        return labExamOrder;
    }

    public void setLabExamOrder(String labExamOrder) {
        this.labExamOrder = labExamOrder;
    }

    @Override
    public String toString() {
        return "LaboratoryOrderModel{" +
                "laboratoryExamsId='" + laboratoryExamsId + '\'' +
                ", laboratoryName='" + laboratoryName + '\'' +
                ", doctorFirstName='" + doctorFirstName + '\'' +
                ", doctorFirstSurname='" + doctorFirstSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", laboratoryOrderDate=" + laboratoryOrderDate +
                ", labExamOrder='" + labExamOrder + '\'' +
                '}';
    }
}
