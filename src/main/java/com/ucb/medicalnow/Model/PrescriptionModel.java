package com.ucb.medicalnow.Model;

import java.util.ArrayList;

public class PrescriptionModel {

    private String description;
    private ArrayList<PrescriptionDetailModel> content;

    public PrescriptionModel() {
    }

    public PrescriptionModel(String description, ArrayList<PrescriptionDetailModel> content) {
        this.description = description;
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<PrescriptionDetailModel> getContent() {
        return content;
    }

    public void setContent(ArrayList<PrescriptionDetailModel> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PrescriptionModel{" +
                "description='" + description + '\'' +
                ", content=" + content +
                '}';
    }
}
