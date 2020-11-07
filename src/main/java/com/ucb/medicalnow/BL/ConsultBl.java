package com.ucb.medicalnow.BL;

import com.mysql.cj.log.Log;
import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.ConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Boolean addConsultToMedicalHistory(int doctorSpecialtyId, String message, byte[] image, int userId) {
        Boolean consultUpdated = null;
        Boolean medicalHistoryExists = medicalHistoryBl.returnMedicalHistoryId(doctorSpecialtyId, userId);
        if (medicalHistoryExists){
            // Introducir una consulta nueva
        } else {
            // crear la historia medica
            // Introducir la consulta
        }


        /*Date consultDate = new SimpleDateFormat("yyyy/MM/dd").parse(patientConsultModel.getConsultDate());
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
        }*/
        return consultUpdated;
    }
}
