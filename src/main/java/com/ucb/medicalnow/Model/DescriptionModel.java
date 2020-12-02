package com.ucb.medicalnow.Model;

import java.util.ArrayList;

public class DescriptionModel {

    private ArrayList<Object> description;

    public DescriptionModel() {
    }

    public DescriptionModel(ArrayList<Object> description) {
        this.description = description;
    }

    public ArrayList<Object> getDescription() {
        return description;
    }

    public void setDescription(ArrayList<Object> description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "DescriptionModel{" +
                "description=" + description +
                '}';
    }
}
