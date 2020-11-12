package com.ucb.medicalnow.Model;

public class MedicalDataModel {

    private Double height;
    private Double weight;
    private String bloodGroup;
    private Double temperature;
    private String pressure;

    public MedicalDataModel() {
    }

    public MedicalDataModel(Double height, Double weight, String bloodGroup, Double temperature, String pressure) {
        this.height = height;
        this.weight = weight;
        this.bloodGroup = bloodGroup;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return "MedicalDataModel{" +
                "height=" + height +
                ", weight=" + weight +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", temperature=" + temperature +
                ", pressure='" + pressure + '\'' +
                '}';
    }
}
