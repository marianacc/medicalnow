package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.PrescriptionDao;
import com.ucb.medicalnow.Model.LaboratoryOrderModel;
import com.ucb.medicalnow.Model.PrescriptionDetailModel;
import com.ucb.medicalnow.Model.PrescriptionModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PrescriptionBl {

    private PrescriptionDao prescriptionDao;

    public PrescriptionBl (PrescriptionDao prescriptionDao) { this.prescriptionDao = prescriptionDao; }

    public ArrayList<PrescriptionModel> returnAllPrescriptionsByUserId (int userId) {
        return this.prescriptionDao.returnAllPrescriptionsByUserId(userId);
    }

    public PrescriptionDetailModel returnPrescriptionDetailByPresctiptionId (int prescriptionId){
        return this.prescriptionDao.returnPrescriptionDetailByPresctiptionId(prescriptionId);
    }
}
