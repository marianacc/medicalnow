package com.ucb.medicalnow.Model;

public class PaymentModel {

    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private String specialtyName;
    private Double qualification;
    private Integer price;

    public PaymentModel() {
    }

    public PaymentModel(String firstName, String firstSurname, String secondSurname, String specialtyName, Double qualification, Integer price) {
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.specialtyName = specialtyName;
        this.qualification = qualification;
        this.price = price;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstSurname() {
        return firstSurname;
    }

    public void setFirstSurname(String firstSurname) {
        this.firstSurname = firstSurname;
    }

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        this.specialtyName = specialtyName;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "PaymentModel{" +
                "firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", specialtyName='" + specialtyName + '\'' +
                ", qualification=" + qualification +
                ", price=" + price +
                '}';
    }
}
