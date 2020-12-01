package com.ucb.medicalnow.Model;

import java.util.ArrayList;

public class PrescriptionModel {

    private String description;
    private ArrayList<PrescriptionDetailModel> prescription_detail;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String description, ArrayList<PrescriptionDetailModel> prescription_detail) {
        this.description = description;
        this.prescription_detail = prescription_detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PrescriptionDetailModel> getPrescription_detail() {
        return prescription_detail;
    }

    public void setPrescription_detail(ArrayList<PrescriptionDetailModel> prescription_detail) {
        this.prescription_detail = prescription_detail;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "description='" + description + '\'' +
                ", prescription_detail=" + prescription_detail +
                '}';
    }
}
