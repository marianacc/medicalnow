package com.ucb.medicalnow.Model;

import java.util.ArrayList;

public class DescriptionModel {

    private ArrayList<DescriptionDetailModel> detail;

    public DescriptionModel() {
    }

    public DescriptionModel(ArrayList<DescriptionDetailModel> detail) {
        this.detail = detail;
    }

    public ArrayList<DescriptionDetailModel> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<DescriptionDetailModel> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "DescriptionModel{" +
                "detail=" + detail +
                '}';
    }
}
