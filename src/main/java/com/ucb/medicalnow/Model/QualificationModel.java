package com.ucb.medicalnow.Model;

public class QualificationModel {

    private Integer qualification;

    public QualificationModel() {
    }

    public QualificationModel(Integer qualification) {
        this.qualification = qualification;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "QualificationModel{" +
                "qualification=" + qualification +
                '}';
    }
}
