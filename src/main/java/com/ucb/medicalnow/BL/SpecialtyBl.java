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

    public ArrayList<SpecialtyModel> returnFreeSpecialties () {
        return this.specialtyDao.returnFreeSpecialties();
    }

    public Map<String, Object> returnDoctorsBySpecialty (int specialtyId) {
        String specialtyName = specialtyDao.returnSpecialtyName(specialtyId);
        ArrayList<DoctorSpecialtyModel> doctorSpecialties = specialtyDao.returnDoctorsBySpecialty(specialtyId);
        for (int i=0; i<doctorSpecialties.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialties.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("body", doctorSpecialties);
        result.put("specialtyName", specialtyName);
        return result;
    }

    public Map<String, Object> returnFreeDoctorsBySpecialty(int specialtyId) {
        String specialtyName = specialtyDao.returnSpecialtyName(specialtyId);
        ArrayList<DoctorSpecialtyModel> doctorSpecialties = specialtyDao.returnFreeDoctorsBySpecialty(specialtyId);
        for (int i=0; i<doctorSpecialties.size(); i++){
            DoctorSpecialtyModel doctorSpecialtyModel = doctorSpecialties.get(i);
            String firstName = doctorSpecialtyModel.getFirstName();
            char firstLetter = firstName.charAt(0);
            doctorSpecialtyModel.setFirstLetter(firstLetter);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("body", doctorSpecialties);
        result.put("specialtyName", specialtyName);
        return result;
    }
}
