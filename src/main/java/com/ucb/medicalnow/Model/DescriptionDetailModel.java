package com.ucb.medicalnow.Model;

public class DescriptionDetailModel {

    private String description;

    public DescriptionDetailModel() {
    }

    public DescriptionDetailModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DescriptionDetailModel{" +
                "description='" + description + '\'' +
                '}';
    }
}
