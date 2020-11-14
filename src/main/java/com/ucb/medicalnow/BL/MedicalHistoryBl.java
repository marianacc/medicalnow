package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.Controller.PatientController;
import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.MedicalHistoryDetailModel;
import com.ucb.medicalnow.Model.MedicalHistoryListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalHistoryBl {

    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;
    private ConsultDao consultDao;

    @Autowired
    public MedicalHistoryBl (MedicalHistoryDao medicalHistoryDao, PatientDao patientDao, ConsultDao consultDao) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
        this.consultDao = consultDao;
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

    public ArrayList<MedicalHistoryListModel> returnAllMedicalHistory (int userId){
        return this.medicalHistoryDao.returnAllMedicalHistoryByUserId(userId);
    }

    public Map<String, Object> returnMedicalHistoryDetail (int medicalHistoryId){
        Map<String, Object> result = new HashMap();
        MedicalHistoryDetailModel medicalHistoryDetailModel = medicalHistoryDao.returnMedicalHistoryDetailByMedicalHistoryId(medicalHistoryId);
        String diagnosis = consultDao.returnDiagnosisByConsultId(medicalHistoryId);
        result.put("patient_data", medicalHistoryDetailModel);
        result.put("diagnosis", diagnosis);
        return result;
    }
}
