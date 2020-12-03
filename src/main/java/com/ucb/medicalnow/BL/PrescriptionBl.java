package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.PrescriptionDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.PrescriptionDetailModel;
import com.ucb.medicalnow.Model.PrescriptionListModel;
import com.ucb.medicalnow.Model.PrescriptionDateListModel;
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

    public ArrayList<PrescriptionListModel> returnConsultsForPatient(int userId) {
        return this.prescriptionDao.returnConsultsForPatient(userId);
    }

    public ArrayList<PrescriptionListModel> returnConsultsForDoctor(int userId) {
        return this.prescriptionDao.returnConsultsForDoctor(userId);
    }

    public Map<String, Object> returnAllPrescriptionsByConsult(int consultId) {
        ArrayList<PrescriptionDateListModel> prescriptions = prescriptionDao.returnAllPrescriptionsByConsult(consultId);
        String specialtyName = specialtyDao.returnSpecialtyIdByConsult(consultId);
        Map<String, Object> response = new HashMap();
        response.put("specialty_name", specialtyName);
        response.put("content", prescriptions);
        return response;
    }

    public Map<String, Object> returnPrescriptionDetail(int prescriptionId){
        ArrayList<PrescriptionDetailModel> detail = prescriptionDao.returnPrescriptionDetail(prescriptionId);
        String description = prescriptionDao.returnDescriptionByPrescription(prescriptionId);
        Map<String, Object> response = new HashMap();
        response.put("prescription_detail", detail);
        response.put("description", description);
        return response;
    }

    public Boolean addPrescriptionDetail (int consultId, String description, ArrayList<PrescriptionDetailModel> prescriptionDetail){
        Boolean prescriptionUpdated = null;
        Integer newPrescriptionResult = prescriptionDao.addNewPrescription(consultId, description);
        if (newPrescriptionResult>0)
        {
            Integer prescriptionId = prescriptionDao.returnMaxPrescriptionId(consultId);
            for (int i = 0; i<prescriptionDetail.size(); i++){
                Integer productsResult = prescriptionDao.insertNewProductsByPrescriptionId(prescriptionId,
                        prescriptionDetail.get(i).getProductName(), prescriptionDetail.get(i).getProductDetail(),
                        prescriptionDetail.get(i).getProductQtty());
                if (productsResult>0){
                    prescriptionUpdated = true;
                } else {
                    prescriptionUpdated = false;
                }
            }
        }
        return prescriptionUpdated;
    }
}
