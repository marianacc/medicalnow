package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.Model.DiagnosisModel;
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

    public Integer searchMedicalHistory(int userId, int doctorSpecialtyId){
        Integer medicalHistoryId = medicalHistoryDao.returnMedicalHistoryId(userId, doctorSpecialtyId);
        if (medicalHistoryId == null){
            Integer medicalHistoryResponse = createMedicalHistory(userId, doctorSpecialtyId);
            if (medicalHistoryResponse > 0){
                medicalHistoryId = medicalHistoryDao.returnMedicalHistoryId(userId, doctorSpecialtyId);
            }
        }
        return medicalHistoryId;
    }

    public Integer createMedicalHistory (int userId, int doctorSpecialtyId) {
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        return this.medicalHistoryDao.createMedicalHistory(patientId, doctorSpecialtyId);
    }










    public Integer returnMedicalHistoryId (int doctorSpecialtyId, int userId){
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        return this.medicalHistoryDao.returnMedicalHistoryId(patientId, doctorSpecialtyId);
    }

    public ArrayList<MedicalHistoryListModel> returnAllMedicalHistory (int userId){
        return this.medicalHistoryDao.returnAllMedicalHistoryByUserId(userId);
    }

    public Map<String, Object> returnMedicalHistoryDetail (int medicalHistoryId){
        Map<String, Object> result = new HashMap();
        MedicalHistoryDetailModel medicalHistoryDetailModel = medicalHistoryDao.returnMedicalHistoryDetailByMedicalHistoryId(medicalHistoryId);
        DiagnosisModel diagnosis = consultDao.returnDiagnosisByConsult(medicalHistoryId);
        result.put("patient_data", medicalHistoryDetailModel);
        result.put("diagnosis", diagnosis);
        return result;
    }
}
