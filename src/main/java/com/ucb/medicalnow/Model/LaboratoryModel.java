package com.ucb.medicalnow.Model;

import java.sql.Date;

public class LaboratoryModel {

    private Integer laboratoryId;
    private String laboratoryName;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Date orderDate;

    public LaboratoryModel() {
    }

    public LaboratoryModel(Integer laboratoryId, String laboratoryName, String firstName, String firstSurname, String secondSurname, String specialtyName, Date orderDate) {
        this.laboratoryId = laboratoryId;
        this.laboratoryName = laboratoryName;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.orderDate = orderDate;
    }

    public Integer getLaboratoryId() {
        return laboratoryId;
    }

    public void setLaboratoryId(Integer laboratoryId) {
        this.laboratoryId = laboratoryId;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "LaboratoryOrderModel{" +
                "laboratoryId=" + laboratoryId +
                ", laboratoryName='" + laboratoryName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }
}
