package com.ucb.medicalnow.Model;

public class DiagnosisModel {

    private String diagnosis;

    public DiagnosisModel() {
    }

    public DiagnosisModel(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "DiagnosisModel{" +
                "diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
