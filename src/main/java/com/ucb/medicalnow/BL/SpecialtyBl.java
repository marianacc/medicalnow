package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpecialtyBl {

    private SpecialtyDao specialtyDao;

    @Autowired
    public SpecialtyBl(SpecialtyDao specialtyDao){ this.specialtyDao = specialtyDao;}

    public ArrayList<SpecialtyModel> returnAllSpecialties () {
        return this.specialtyDao.returnAllSpecialties();
    }

    public ArrayList<DoctorSpecialtyModel> returnDoctorsBySpecialty (int specialtyId) { return this.specialtyDao.returnDoctorsBySpecialty(specialtyId); }

}
