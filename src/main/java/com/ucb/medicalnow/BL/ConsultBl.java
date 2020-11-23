package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.ConsultModel;
import com.ucb.medicalnow.Model.DiagnosisModel;
import com.ucb.medicalnow.Model.StoredConsultModel;
import com.ucb.medicalnow.Model.PaymentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ConsultBl {

    private ConsultDao consultDao;
    private PatientDao patientDao;
    private SpecialtyDao specialtyDao;

    @Autowired
    public ConsultBl (ConsultDao consultDao, PatientDao patientDao, SpecialtyDao specialtyDao){
        this.consultDao = consultDao;
        this.patientDao = patientDao;
        this.specialtyDao = specialtyDao;
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

    public DiagnosisModel returnDiagnosisByConsult(int consultId){
        return this.consultDao.returnDiagnosisByConsult(consultId);
    }

    public PaymentModel returnInfoForPayment(int doctorSpecialtyId){
        return this.consultDao.returnInfoByDoctorSpecialty(doctorSpecialtyId);
    }

    public Boolean addQualificationByConsult(int consultId, int qualification){
        Integer doctorSpecialtyId = specialtyDao.returnDoctorSpecialtyIdByConsult(consultId);
        Integer patientId = patientDao.returnPatientIdByConsult(consultId);
        Integer consultResponse = consultDao.addQualificationByConsult(doctorSpecialtyId, patientId, qualification);
        Boolean registryUpdated = null;
        if(consultResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public Boolean storeConsult(int consultId){
        Boolean registryUpdated = null;
        Integer storeConsultResponse = consultDao.storeConsult(consultId);
        if (storeConsultResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public Boolean activateConsult(int consultId){
        Boolean registryUpdated = null;
        Integer activateConsultResponse = consultDao.activateConsult(consultId);
        if (activateConsultResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public ArrayList<StoredConsultModel> returnAllStoredConsults(int userId){
        return this.consultDao.returnAllStoredConsults(userId);
    }
}
