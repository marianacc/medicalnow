package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.PrescriptionDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.LaboratoryOrderModel;
import com.ucb.medicalnow.Model.PrescriptionDetailModel;
import com.ucb.medicalnow.Model.PrescriptionListModel;
import com.ucb.medicalnow.Model.PrescriptionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class PrescriptionBl {

    private PrescriptionDao prescriptionDao;
    private SpecialtyDao specialtyDao;

    public PrescriptionBl (PrescriptionDao prescriptionDao, SpecialtyDao specialtyDao) {
        this.prescriptionDao = prescriptionDao;
        this.specialtyDao = specialtyDao;
    }

    public ArrayList<PrescriptionListModel> returnAllConsultsWithPrescriptions (int userId) {
        return this.prescriptionDao.returnAllConsultsThatHavePrescriptions(userId);
    }

    public Map<String, Object> returnAllPrescriptionsByConsultId (int consultId) {
        Map<String, Object> result = new HashMap();
        ArrayList<PrescriptionModel> prescriptions = prescriptionDao.returnAllPrescriptionsByConsultId(consultId);
        String specialtyName = specialtyDao.returnSpecialtyIdByConsultId(consultId);
        result.put("specialty_name", specialtyName);
        result.put("content", prescriptions);
        return result;
    }

    public Map<String, Object> returnPrescriptionDetailByPrescriptionId (int prescriptionId){
        Map<String, Object> result = new HashMap();
        PrescriptionDetailModel detail = prescriptionDao.returnPrescriptionDetailByPresctiptionId(prescriptionId);
        String description = prescriptionDao.returnDescriptionByPrescriptionId(prescriptionId);
        result.put("prescription_detail", detail);
        result.put("description", description);
        return result;
    }
}
