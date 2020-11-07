package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import com.ucb.medicalnow.Model.UserAvatarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class SpecialtyBl {

    private SpecialtyDao specialtyDao;

    @Autowired
    public SpecialtyBl(SpecialtyDao specialtyDao){ this.specialtyDao = specialtyDao;}

    public ArrayList<SpecialtyModel> returnAllSpecialties () {
        return this.specialtyDao.returnAllSpecialties();
    }

    public Map returnAllGeneralMedicineDoctors () {
        ArrayList<DoctorSpecialtyModel> doctorSpecialtyResponse = specialtyDao.returnGeneralMedicineDoctors();
        for (int i=0; i<doctorSpecialtyResponse.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialtyResponse.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        Map result = new HashMap<>();
        result.put("body", doctorSpecialtyResponse);
        result.put("specialtyName", "Medicina General");
        return result;
    }

    public Map returnDoctorsBySpecialty (int specialtyId) {
        String specialtyName = specialtyDao.returnSpecialtyNameBySpecialtyId(specialtyId);
        ArrayList<DoctorSpecialtyModel> doctorSpecialtyResponse = specialtyDao.returnDoctorsBySpecialty(specialtyId);
        for (int i=0; i<doctorSpecialtyResponse.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialtyResponse.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        Map result = new HashMap<>();
        result.put("body", doctorSpecialtyResponse);
        result.put("specialtyName", specialtyName);
        return result;
    }
}
