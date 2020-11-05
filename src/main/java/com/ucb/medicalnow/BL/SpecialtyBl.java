package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import com.ucb.medicalnow.Model.UserAvatarModel;
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

    public ArrayList<DoctorSpecialtyModel> returnDoctorsBySpecialty (int specialtyId) {
        ArrayList<DoctorSpecialtyModel> doctorSpecialtyResponse = specialtyDao.returnDoctorsBySpecialty(specialtyId);
        for (int i=0; i<doctorSpecialtyResponse.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialtyResponse.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        return doctorSpecialtyResponse;
    }

    public ArrayList<DoctorSpecialtyModel> returnGeneralMedicineDoctors () {
        ArrayList<DoctorSpecialtyModel> doctorSpecialtyResponse = specialtyDao.returnGeneralMedicineDoctors();
        for (int i=0; i<doctorSpecialtyResponse.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialtyResponse.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        return doctorSpecialtyResponse;
    }
}
