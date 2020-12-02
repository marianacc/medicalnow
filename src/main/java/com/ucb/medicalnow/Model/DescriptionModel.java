package com.ucb.medicalnow.Model;

import java.util.ArrayList;

public class DescriptionModel {

    private ArrayList<String> description;

    public DescriptionModel() {
    }

    public DescriptionModel(ArrayList<String> description) {
        this.description = description;
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<String> description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DescriptionModel{" +
                "description=" + description +
                '}';
    }
}
