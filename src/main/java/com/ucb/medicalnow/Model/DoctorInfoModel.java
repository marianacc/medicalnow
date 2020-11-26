package com.ucb.medicalnow.Model;

import java.sql.Time;

public class DoctorInfoModel {

    private Integer price;
    private Time fromTime;
    private Time toTime;

    public DoctorInfoModel() {
    }

    public DoctorInfoModel(Integer price, Time fromTime, Time toTime) {
        this.price = price;
        this.fromTime = fromTime;
        this.toTime = toTime;
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

    @Override
    public String toString() {
        return "DoctorInfoModel{" +
                "price=" + price +
                ", fromTime=" + fromTime +
                ", toTime=" + toTime +
                '}';
    }
}
