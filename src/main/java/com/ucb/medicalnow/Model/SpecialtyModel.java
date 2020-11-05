package com.ucb.medicalnow.Model;

import java.sql.Date;

public class SpecialtyModel {

    private Integer specialtyId;
    private String specialtyName;
    private Integer specialtyQuantity;
    private String specialtyImage;

    public SpecialtyModel() {
    }

    public SpecialtyModel(Integer specialtyId, String specialtyName, Integer specialtyQuantity, String specialtyImage) {
        this.specialtyId = specialtyId;
        this.specialtyName = specialtyName;
        this.specialtyQuantity = specialtyQuantity;
        this.specialtyImage = specialtyImage;
    }

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Integer getSpecialtyQuantity() {
        return specialtyQuantity;
    }

    public void setSpecialtyQuantity(Integer specialtyQuantity) {
        this.specialtyQuantity = specialtyQuantity;
    }

    public String getSpecialtyImage() {
        return specialtyImage;
    }

    public void setSpecialtyImage(String specialtyImage) {
        this.specialtyImage = specialtyImage;
    }
}
