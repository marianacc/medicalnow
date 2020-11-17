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

    public ArrayList<PrescriptionListModel> returnAllConsultsWithPrescriptions (int userId) {
        return this.prescriptionDao.returnAllConsultsThatHavePrescriptions(userId);
    }

    public Map<String, Object> returnAllPrescriptionsByConsultId (int consultId) {
        Map<String, Object> result = new HashMap();
        ArrayList<PrescriptionDateListModel> prescriptions = prescriptionDao.returnAllPrescriptionsByConsultId(consultId);
        String specialtyName = specialtyDao.returnSpecialtyIdByConsultId(consultId);
        result.put("specialty_name", specialtyName);
        result.put("content", prescriptions);
        return result;
    }

    public Map<String, Object> returnPrescriptionDetailByPrescriptionId (int prescriptionId){
        Map<String, Object> result = new HashMap();
        ArrayList<PrescriptionDetailModel> detail = prescriptionDao.returnPrescriptionDetailByPresctiptionId(prescriptionId);
        String description = prescriptionDao.returnDescriptionByPrescriptionId(prescriptionId);
        result.put("prescription_detail", detail);
        result.put("description", description);
        return result;
    }

    public Boolean addPrescriptionDetail (int consultId, String description, ArrayList<PrescriptionDetailModel> prescriptionDetail){
        Boolean prescriptionUpdated = null;
        Integer prescriptionResult = prescriptionDao.addNewPrescription(consultId, description);
        if (prescriptionResult>0)
        {
            Integer prescriptionId = prescriptionDao.returnMaxPrescriptionId(consultId);
            for (int i = 0; i<prescriptionDetail.size(); i++){
                Integer productsResult = prescriptionDao.insertNewProductsByPrescriptionId(prescriptionId, prescriptionDetail.get(i).getProductName(),
                        prescriptionDetail.get(i).getProductDetail(), prescriptionDetail.get(i).getProductQtty());

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
