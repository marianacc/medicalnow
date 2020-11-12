package com.ucb.medicalnow.Model;

public class AllergyModel {

    private String description;

    public AllergyModel() {
    }

    public AllergyModel(String description) {
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
        return "AllergyModel{" +
                "description='" + description + '\'' +
                '}';
    }
}
