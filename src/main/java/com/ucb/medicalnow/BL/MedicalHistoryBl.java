package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryBl {

    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;

    @Autowired
    public MedicalHistoryBl (MedicalHistoryDao medicalHistoryDao, PatientDao patientDao) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
    }

    public void verifyMedicalHistoryExistence (int userId){
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer medicalHistoryId = medicalHistoryDao.returnMedicalHistoryIdByPatientId(patientId);
        System.out.print(medicalHistoryId);
        /*        if(medicalHistoryId != null){
            System.out.print(medicalHistoryId);
        } else
        {

            Integer medicalHistoryCreated = medicalHistoryDao.createAMedicalHistory(patientId);
            if (medicalHistoryCreated > 0){
                medicalHistoryId = medicalHistoryDao.returnMaxMedicalHistoryId();
                System.out.print(medicalHistoryId);
            }
        }*/
    }
}
