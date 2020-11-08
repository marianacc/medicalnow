package com.ucb.medicalnow.Model;

import com.ucb.medicalnow.DAO.ConsultDao;

public class ConsultIdModel {

    private Integer consultId;

    public ConsultIdModel() {
    }

    public ConsultIdModel(Integer consultId) {
        this.consultId = consultId;
    }

    public Integer getConsultId() {
        return consultId;
    }

    public void setConsultId(Integer consultId) {
        this.consultId = consultId;
    }

    @Override
    public String toString() {
        return "ConsultIdModel{" +
                "consultId=" + consultId +
                '}';
    }
}
