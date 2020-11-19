package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.*;
import com.ucb.medicalnow.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class MedicalHistoryBl {

    private MedicalHistoryDao medicalHistoryDao;
    private PatientDao patientDao;
    private ConsultDao consultDao;
    private PrescriptionDao prescriptionDao;
    private DoctorDao doctorDao;

    @Autowired
    public MedicalHistoryBl (MedicalHistoryDao medicalHistoryDao, PatientDao patientDao, ConsultDao consultDao, PrescriptionDao prescriptionDao, DoctorDao doctorDao) {
        this.medicalHistoryDao = medicalHistoryDao;
        this.patientDao = patientDao;
        this.consultDao = consultDao;
        this.prescriptionDao = prescriptionDao;
        this.doctorDao = doctorDao;
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

    public Integer createMedicalHistory(int userId, int doctorSpecialtyId) {
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        return this.medicalHistoryDao.createMedicalHistory(patientId, doctorSpecialtyId);
    }

    public ArrayList<MedicalHistoryListModel> returnAllMedicalHistory(int userId){
        return this.medicalHistoryDao.returnAllMedicalHistory(userId);
    }

    public Map<String, Object> returnConsultsByMedicalHistory (int medicalHistoryId){
        ArrayList<MedicalHistoryDateListModel> medicalHistoryDateList = medicalHistoryDao.returnConsultsByMedicalHistory(medicalHistoryId);
        DoctorNameModel doctorNameModel = doctorDao.returnDoctorSpecialtyNameByMedicalHistory(medicalHistoryId);
        Map<String, Object> response = new HashMap<>();
        response.put("content", medicalHistoryDateList);
        response.put("doctorInfo", doctorNameModel);
        return response;
    }

    public Map<String, Object> returnMedicalHistoryDetailByConsult(int consultId){
        MedicalHistoryDetailModel medicalHistoryDetailModel = medicalHistoryDao.returnMedicalHistoryDetailByConsult(consultId);
        String diagnosis = consultDao.returnDiagnosisByConsult(consultId);
        ArrayList<Integer> prescriptionIdList = prescriptionDao.returnPrescriptionIdByConsult(consultId);
        Map<String, Object> result = new HashMap();
        result.put("patient_data", medicalHistoryDetailModel);
        result.put("diagnosis", diagnosis);
        result.put("prescriptionId", prescriptionIdList);
        return result;
    }
}
