package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConsultDao;
import com.ucb.medicalnow.DAO.DoctorDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.Model.ConsultsDoctorModel;
import com.ucb.medicalnow.Model.ConsultsPatientModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConsultBl {

    private PatientDao patientDao;
    private ConsultDao consultDao;
    private DoctorDao doctorDao;

    @Autowired
    public ConsultBl (PatientDao patientDao, ConsultDao consultDao, DoctorDao doctorDao){
        this.patientDao = patientDao;
        this.consultDao = consultDao;
        this.doctorDao = doctorDao;
    }

    public Map<String, Object> consultExists(int medicalHistoryId){
        Map<String, Object> result = new HashMap();
        Boolean consultResponse = null;
        Long consultId = consultDao.returnConsultId(medicalHistoryId);
        if (consultId == null){
            consultResponse = false;
        } else {
            consultResponse = true;
        }
        result.put("id", consultId);
        result.put("exists", consultResponse);
        return result;
    }

    public Boolean createNewConsult (int medicalHistoryId){
        Boolean consultResponse = null;
        Integer result = consultDao.createNewConsult(medicalHistoryId);
        if (result > 0){
            consultResponse = true;
        } else {
            consultResponse = false;
        }
        return consultResponse;
    }

    public Long returnConsultId (int medicalHistory){
        return this.consultDao.returnConsultId(medicalHistory);
    }

    public ArrayList<ConsultsPatientModel> returnAllConsultsByPatientId (int userId){
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        return this.consultDao.returnAllConsultsByPatientId(patientId);
    }

    public ArrayList<ConsultsDoctorModel> returnAllConsultsByDoctorId (int userId){
        Integer doctorId = doctorDao.returnDoctorIdByUserId(userId);
        return this.consultDao.returnAllConsultsByDoctorId(doctorId);
    }
}
