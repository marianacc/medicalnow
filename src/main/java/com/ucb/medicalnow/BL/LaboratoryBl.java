package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.LaboratoryDao;
import com.ucb.medicalnow.Model.LaboratoryOrderModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class LaboratoryBl {

    private LaboratoryDao laboratoryDao;

    @Autowired
    public LaboratoryBl (LaboratoryDao laboratoryDao) {this.laboratoryDao = laboratoryDao; }

    public ArrayList<LaboratoryOrderModel> returnAllLaboratoriesByPatient(int patientId) { return this.laboratoryDao.returnAllLaboratoriesByPatient(patientId); }
}
