package com.ucb.medicalnow.Model;

import java.sql.Date;

public class LaboratoryOrderModel {

    private String laboratoryExamsId;
    private String laboratoryName;
    private String doctorFirstName;
    private String doctorFirstsurname;
    private String specialtyName;
    private Date laboratoryOrderDate;

    public LaboratoryOrderModel() {
    }

    public LaboratoryOrderModel(String laboratoryExamsId, String laboratoryName, String doctorFirstName, String doctorFirstsurname, String specialtyName, Date laboratoryOrderDate) {
        this.laboratoryExamsId = laboratoryExamsId;
        this.laboratoryName = laboratoryName;
        this.doctorFirstName = doctorFirstName;
        this.doctorFirstsurname = doctorFirstsurname;
        this.specialtyName = specialtyName;
        this.laboratoryOrderDate = laboratoryOrderDate;
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

    public String getDoctorFirstsurname() {
        return doctorFirstsurname;
    }

    public void setDoctorFirstsurname(String doctorFirstsurname) {
        this.doctorFirstsurname = doctorFirstsurname;
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

    @Override
    public String toString() {
        return "LaboratoryOrderModel{" +
                "laboratoryExamsId='" + laboratoryExamsId + '\'' +
                ", laboratoryName='" + laboratoryName + '\'' +
                ", doctorFirstName='" + doctorFirstName + '\'' +
                ", doctorFirstsurname='" + doctorFirstsurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", laboratoryOrderDate=" + laboratoryOrderDate +
                '}';
    }
}
