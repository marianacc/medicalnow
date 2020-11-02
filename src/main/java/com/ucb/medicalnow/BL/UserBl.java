package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.UserAvatarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserBl {

    private UserDao userDao;

    @Autowired
    public UserBl (UserDao userDao) { this.userDao = userDao; }

    public ArrayList<UserAvatarModel> returnUserNameByPatientId (int patientId){
        ArrayList<UserAvatarModel> userAvatarResponse = userDao.returnUserNameByPatientId(patientId);
        UserAvatarModel userAvatarModel = userAvatarResponse.get(0);
        String firstName = userAvatarModel.getUserFirstName();
        char firstLetter = firstName.charAt(0);
        userAvatarModel.setFirstLetter(firstLetter);
        return userAvatarResponse;
    }
}
