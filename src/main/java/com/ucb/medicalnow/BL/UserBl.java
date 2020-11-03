package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserConfigurationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        ArrayList<UserAvatarModel> userAvatarResponse = userDao.returnUserNameByUserId(userId);
        UserAvatarModel userAvatarModel = userAvatarResponse.get(0);
        String firstName = userAvatarModel.getUserFirstName();
        char firstLetter = firstName.charAt(0);
        userAvatarModel.setFirstLetter(firstLetter);
        return userAvatarModel;
    }

    public ArrayList<UserConfigurationModel> returnUserConfigurationByUserId(int userId){
        return this.userDao.returnUserConfigurationByUserId(userId);
    }

    public Boolean updateConfigurationByUserId (UserConfigurationModel UserConfigurationModel, int userId){
        Boolean configUpdated = null;
        Integer personId = personDao.returnPersonIdByUserId(userId);
        Integer personResponse = personDao.updatePerson(UserConfigurationModel.getFirstName(), UserConfigurationModel.getFirstSurname(),
                UserConfigurationModel.getSecondSurname(), UserConfigurationModel.getBirthDate(), UserConfigurationModel.getCity(), personId);
        if (personResponse > 0){
            Integer userResponse = userDao.updateUser(UserConfigurationModel.getEmail(), UserConfigurationModel.getPassword(),
                    UserConfigurationModel.getPhoneNumber(), userId);
            if (userResponse > 0){
                Integer patientId = patientDao.returnPatientIdByUserId(userId);
                Integer patientResponse = patientDao.updatePatient(UserConfigurationModel.getWeight(), UserConfigurationModel.getHeight(), patientId);
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
