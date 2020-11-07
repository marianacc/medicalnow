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

    public Map medicalHistoryExists(int doctorSpecialtyId, int userId){
        Map result = new HashMap();
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

    /*
    public Boolean createMedicalHistory (int userId) {
        Boolean medicalHistoryUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer medicalHistoryResponse = medicalHistoryDao.newMedicalHistoryByPatientId(patientId, );
        if(medicalHistoryResponse>0){
            medicalHistoryUpdated = true;
        } else {
            medicalHistoryUpdated = false;
        }
        return medicalHistoryUpdated;
    }*/

}
