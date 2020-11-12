package com.ucb.medicalnow.BL;

import com.google.common.hash.Hashing;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserConfigurationModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;

@Service
public class UserBl {

    private UserDao userDao;
    private PersonDao personDao;
    private PatientDao patientDao;

    @Value("${medicalnow.security.salt}")
    private String salt;

    @Autowired
    public UserBl (UserDao userDao, PersonDao personDao, PatientDao patientDao) {
        this.userDao = userDao;
        this.personDao = personDao;
        this.patientDao = patientDao;
    }

    public UserAvatarModel returnUserNameByUserId(int userId){
        UserAvatarModel userAvatarModel = userDao.returnUserNameByUserId(userId);
        String firstName = userAvatarModel.getFirstName();
        char firstLetter = firstName.charAt(0);
        userAvatarModel.setFirstLetter(firstLetter);
        return userAvatarModel;
    }

    public Boolean updateMedicalData (Double weight, Double height, String bloodGroup, Double temperature, String pressure, int userId){
        Boolean medicalDataUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer patientResponse = patientDao.updateMedicalDataByPatientId(weight, height, bloodGroup, temperature, pressure, patientId);
        if (patientResponse > 0){
            medicalDataUpdated = true;
        } else {
            medicalDataUpdated = false;
        }
        return medicalDataUpdated;
    }

    public Boolean addNewAllergy (int userId, String description){
        Boolean allergyUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUserId(userId);
        Integer patientResponse = patientDao.insertNewAllergyByPatientId(patientId, description);
        if (patientResponse > 0){
            allergyUpdated = true;
        } else {
            allergyUpdated = false;
        }
        return allergyUpdated;
    }

    /*
    public UserConfigurationModel returnUserConfigurationByUserId(int userId){
        return this.userDao.returnUserConfigurationByUserId(userId);
    }

    public Boolean updateConfigurationByUserId (String firstName, String firstSurname, String secondSurname, String phoneNumber, Date birthDate, Double weight, Double height, String city, String email, String password, int userId){
        Boolean configUpdated = null;
        // Agregarle la Salt y aplicar el algoritmo hash256 a la contraseña para guardarla en la base de datos
        String sha256hex= Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();
        Integer personId = personDao.returnPersonIdByUserId(userId);
        Integer personResponse = personDao.updatePerson(firstName, firstSurname, secondSurname, birthDate, city, personId);
        if (personResponse > 0){
            Integer userResponse = userDao.updateUser(email, sha256hex, phoneNumber, userId);
            if (userResponse > 0){
                Integer patientId = patientDao.returnPatientIdByUserId(userId);
                Integer patientResponse = patientDao.updatePatient(weight, height, patientId);
                if (patientResponse > 0){
                    configUpdated = true;
                } else {
                    configUpdated = false;
                }
            }
        }
        return configUpdated;
    }*/
}
