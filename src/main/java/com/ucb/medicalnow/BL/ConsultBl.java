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
public class ConsultBl {

    private ConsultDao consultDao;
    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;
    private SpecialtyDao specialtyDao;

    @Autowired
    public ConsultBl (ConsultDao consultDao, MedicalHistoryDao medicalHistoryDao, PatientDao patientDao, SpecialtyDao specialtyDao) {
        this.consultDao = consultDao;
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
        this.specialtyDao = specialtyDao;
    }

    public Boolean addConsultToMedicalHistory(PatientConsultModel patientConsultModel, int userId) throws ParseException {
        Date consultDate = new SimpleDateFormat("yyyy/MM/dd").parse(patientConsultModel.getConsultDate());
        Boolean consultUpdated = null;
        Integer medicalHistoryId = medicalHistoryDao.returnMaxMedicalHistoryId();
        System.out.print(medicalHistoryId);
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        System.out.print(patientId);
        Integer doctorSpecialtyId = specialtyDao.returnDoctorSpecialtyIdByDoctorId(patientConsultModel.getDoctorId());
        System.out.print(doctorSpecialtyId);
        Integer consultResponse = consultDao.addConsult(medicalHistoryId, patientId, doctorSpecialtyId, patientConsultModel.getMessage(), consultDate);
        if (consultResponse > 0){
            consultUpdated = true;
        } else {
            consultUpdated = false;
        }
        return consultUpdated;
    }
}
