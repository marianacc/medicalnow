package com.ucb.medicalnow.Model;

import java.sql.Time;

public class DoctorSpecialtyModel {

    private Integer doctorSpecialtyId;
    private char firstLetter;
    private String firstName;
    private String firstSurname;
    private String secondSurname;
    private Integer price;
    private Time fromTime;
    private Time toTime;
    private Double qualification;

    public DoctorSpecialtyModel() {
    }

    public DoctorSpecialtyModel(Integer doctorSpecialtyId, char firstLetter, String firstName, String firstSurname, String secondSurname, Integer price, Time fromTime, Time toTime, Double qualification) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.firstLetter = firstLetter;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.price = price;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.qualification = qualification;
    }

    public DoctorSpecialtyModel(Integer doctorSpecialtyId, String firstName, String firstSurname, String secondSurname, Integer price, Time fromTime, Time toTime, Double qualification) {
        this.doctorSpecialtyId = doctorSpecialtyId;
        this.firstName = firstName;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.price = price;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.qualification = qualification;
    }

    public Integer getDoctorSpecialtyId() {
        return doctorSpecialtyId;
    }

    public void setDoctorSpecialtyId(Integer doctorSpecialtyId) {
        this.doctorSpecialtyId = doctorSpecialtyId;
    }

    public char getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(char firstLetter) {
        this.firstLetter = firstLetter;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Time getFromTime() {
        return fromTime;
    }

    public void setFromTime(Time fromTime) {
        this.fromTime = fromTime;
    }

    public Time getToTime() {
        return toTime;
    }

    public void setToTime(Time toTime) {
        this.toTime = toTime;
    }

    public Double getQualification() {
        return qualification;
    }

    public void setQualification(Double qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "DoctorSpecialtyModel{" +
                "doctorSpecialtyId=" + doctorSpecialtyId +
                ", firstLetter=" + firstLetter +
                ", firstName='" + firstName + '\'' +
                ", firstSurname='" + firstSurname + '\'' +
                ", secondSurname='" + secondSurname + '\'' +
                ", price=" + price +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                ", qualification=" + qualification +
                '}';
    }
}

