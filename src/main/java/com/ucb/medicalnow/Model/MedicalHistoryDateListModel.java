package com.ucb.medicalnow.Model;

import java.sql.Date;

public class MedicalHistoryDateListModel {

    private Integer consultId;
    private Date startDate;
    private Integer status;

    public MedicalHistoryDateListModel() {
    }

    public MedicalHistoryDateListModel(Integer consultId, Date startDate, Integer status) {
        this.consultId = consultId;
        this.startDate = startDate;
        this.status = status;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MedicalHistoryDateListModel{" +
                "consultId=" + consultId +
                ", startDate=" + startDate +
                ", status=" + status +
                '}';
    }
}
