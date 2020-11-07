package com.ucb.medicalnow.BL;

import com.mysql.cj.log.Log;
import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.ConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConsultBl {

    private ConsultDao consultDao;
    private PatientDao patientDao;
    private SpecialtyDao specialtyDao;
    private MedicalHistoryBl medicalHistoryBl;

    @Autowired
    public ConsultBl (ConsultDao consultDao, PatientDao patientDao, SpecialtyDao specialtyDao, MedicalHistoryBl medicalHistoryBl) {
        this.consultDao = consultDao;
        this.patientDao = patientDao;
        this.specialtyDao = specialtyDao;
        this.medicalHistoryBl = medicalHistoryBl;
    }

    public void addConsultToMedicalHistory(int doctorSpecialtyId, String message, byte[] image, int userId) {
        Map medicalHistoryResponse = medicalHistoryBl.medicalHistoryExists(doctorSpecialtyId, userId);

//        System.out.println("ID del usuario"+medicalHistoryResponse.get("id"));
//        System.out.println("Existe?"+medicalHistoryResponse.get("exists"));
    }
}
