package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.PatientConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class MedicalHistoryBl {

    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;

    @Autowired
    public MedicalHistoryBl (MedicalHistoryDao medicalHistoryDao, PatientDao patientDao) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
    }

    public Boolean createMedicalHistory (int userId) throws ParseException {
        Boolean medicalHistoryUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer medicalHistoryResponse = medicalHistoryDao.newMedicalHistoryByPatientId(patientId);
        if(medicalHistoryResponse>0){
            medicalHistoryUpdated = true;
        } else {
            medicalHistoryUpdated = false;
        }
        return medicalHistoryUpdated;
    }
}
