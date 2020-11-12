package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.Controller.PatientController;
import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalHistoryBl {

    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;

    @Autowired
    public MedicalHistoryBl (MedicalHistoryDao medicalHistoryDao, PatientDao patientDao) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
    }

    public Map<String, Object> medicalHistoryExists (int doctorSpecialtyId, int userId){
        Map<String, Object> result = new HashMap();
        Boolean medicalHistoryResponse = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Long medicalHistoryId = medicalHistoryDao.returnMedicalHistoryId(patientId, doctorSpecialtyId);
        if (medicalHistoryId == null){
            medicalHistoryResponse = false;
        } else {
            medicalHistoryResponse = true;
        }
        result.put("id", medicalHistoryId);
        result.put("exists", medicalHistoryResponse);
        return result;
    }

    public Boolean createMedicalHistory (int userId, int doctorSpecialtyId) {
        Boolean medicalHistoryUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer medicalHistoryResponse = medicalHistoryDao.createMedicalHistory(patientId, doctorSpecialtyId);
        if(medicalHistoryResponse>0){
            medicalHistoryUpdated = true;
        } else {
            medicalHistoryUpdated = false;
        }
        return medicalHistoryUpdated;
    }

    public Long returnMedicalHistoryId (int doctorSpecialtyId, int userId){
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        return this.medicalHistoryDao.returnMedicalHistoryId(patientId, doctorSpecialtyId);
    }
}
