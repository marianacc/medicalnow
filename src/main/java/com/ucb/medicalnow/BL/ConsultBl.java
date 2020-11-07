package com.ucb.medicalnow.BL;

import com.mysql.cj.log.Log;
import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.ConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultBl {

    /*
    public Map<String, Object> consultExists(int doctorSpecialtyId, int userId){
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
    }*/
}
