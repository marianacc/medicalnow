package com.ucb.medicalnow.BL;

import com.google.common.hash.Hashing;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserConfigurationModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;

@Service
public class UserBl {

    private UserDao userDao;
    private PersonDao personDao;
    private PatientDao patientDao;

    @Autowired
    public UserBl (UserDao userDao, PersonDao personDao, PatientDao patientDao) {
        this.userDao = userDao;
        this.personDao = personDao;
        this.patientDao = patientDao;
    }

    public UserAvatarModel returnUserNameByUserId(int userId){
        UserAvatarModel userAvatarModel = userDao.returnUserNameByUserId(userId);
        String firstName = userAvatarModel.getUserFirstName();
        char firstLetter = firstName.charAt(0);
        userAvatarModel.setFirstLetter(firstLetter);
        return userAvatarModel;
    }

    public UserConfigurationModel returnUserConfigurationByUserId(int userId){
        return this.userDao.returnUserConfigurationByUserId(userId);
    }

    public Boolean updateConfigurationByUserId (String firstName, String firstSurname, String secondSurname, String phoneNumber, Date birthDate, Double weight, Double height, String city, String email, int userId){
        Boolean configUpdated = null;
        Integer personId = personDao.returnPersonIdByUserId(userId);
        Integer personResponse = personDao.updatePerson(firstName, firstSurname, secondSurname, birthDate, city, personId);
        if (personResponse > 0){
            Integer userResponse = userDao.updateUser(email, phoneNumber, userId);
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
    }
}
