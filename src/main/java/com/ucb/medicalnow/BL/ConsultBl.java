package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.DoctorDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.Model.ConsultModel;
import com.ucb.medicalnow.Model.DiagnosisModel;
import com.ucb.medicalnow.Model.PaymentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultBl {

    private ConsultDao consultDao;

    @Autowired
    public ConsultBl (ConsultDao consultDao){
        this.consultDao = consultDao;
    }

    public Integer createNewConsult(int medicalHistoryId){
        Integer consultId = null;
        Integer newConsultResponse = consultDao.createNewConsult(medicalHistoryId);
        if (newConsultResponse > 0){
            consultId = consultDao.returnConsultId(medicalHistoryId);
        }
        return consultId;
    }

    public ArrayList<ConsultModel> returnAllConsultsByPatient(int userId){
        return this.consultDao.returnAllConsultsByPatient(userId);
    }

    public ArrayList<ConsultModel> returnAllConsultsByDoctor (int userId){
        return this.consultDao.returnAllConsultsByDoctor(userId);
    }

    public Boolean dischargeUserByConsultId(int consultId){
        Boolean registryUpdated = null;
        Integer dischargeUserResponse = consultDao.dischargeUserByConsultId(consultId);
        if (dischargeUserResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public Boolean addDiagnosisByConsult(String diagnosis, int consultId){
        Boolean registryUpdated = null;
        Integer diagnosisResponse = consultDao.addDiagnosisByConsult(diagnosis, consultId);
        if (diagnosisResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public String returnDiagnosisByConsult(int consultId){
        return this.consultDao.returnDiagnosisByConsult(consultId);
    }

    public PaymentModel returnInfoForPayment(int doctorSpecialtyId){
        return this.consultDao.returnInfoByDoctorSpecialty(doctorSpecialtyId);
    }
}
