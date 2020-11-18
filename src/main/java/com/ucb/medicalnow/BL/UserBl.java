package com.ucb.medicalnow.BL;

import com.google.common.hash.Hashing;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.MedicalDataModel;
import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

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

    public Boolean addNewPatient(String firstName, String firstSurname, String secondSurname, Date birthDate,
                                  String email, String password, String phoneNumber) {

        Boolean registryUpdated = null;

        Integer addNewPersonResponse = personDao.addNewPerson(firstName, firstSurname, secondSurname, birthDate);
        if (addNewPersonResponse > 0){
            Integer personId = personDao.returnMaxPersonId();
            String sha256hex = hash256toPassword(password);
            Integer addNewUserResponse = userDao.addNewUser(personId, email, sha256hex, phoneNumber);
            if (addNewUserResponse > 0){
                Integer userId = userDao.returnMaxUserId();
                Integer addNewUserRoleResponse = userDao.addNewUserRole(userId);
                if (addNewUserRoleResponse > 0){
                    Integer addNewPatientResponse = patientDao.addNewPatient(personId, userId);
                    if (addNewPatientResponse > 0){
                        registryUpdated = true;
                    } else {
                        registryUpdated = false;
                    }
                }
            }
        }
        return registryUpdated;
    }

    public UserAvatarModel returnAvatarByUser(int userId){
        UserAvatarModel userAvatarModel = userDao.returnUserNameByUser(userId);
        String firstName = userAvatarModel.getFirstName();
        char firstLetter = firstName.charAt(0);
        userAvatarModel.setFirstLetter(firstLetter);
        return userAvatarModel;
    }

    public MedicalDataModel returnMedicalData(int userId){ return this.patientDao.returnMedicalDataByPatient(userId); }

    public Boolean updateMedicalData(int userId, Double weight, Double height, String bloodGroup, Double temperature, String pressure){
        Boolean registryUpdated = null;
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        Integer medicalDataUpdateResponse = patientDao.updateMedicalDataByPatient(weight, height, bloodGroup, temperature, pressure, patientId);
        if (medicalDataUpdateResponse > 0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public UserDataModel returnUserInformation(int userId){
        return this.userDao.returnUserInformationByUser(userId);
    }

    public Boolean updateDataByUser(int userId, String firstName, String firstSurname, String secondSurname,
                                    Date birthDate, String email, String password,String phoneNumber){
        Boolean registryUpdated = null;

        Integer personId = personDao.returnPersonIdByUser(userId);
        Integer updatePersonResponse = personDao.updatePersonData(firstName, firstSurname, secondSurname, birthDate, personId);
        if (updatePersonResponse > 0){
            String sha256hex = hash256toPassword(password);
            Integer updateUserResponse = userDao.updateUserData(email, sha256hex, phoneNumber, userId);
            if (updateUserResponse > 0){
                registryUpdated = true;
            } else {
                registryUpdated = false;
            }
        }
        return registryUpdated;
    }

    public String hash256toPassword(String password){
        // Se aplica la función hash 256+salt a la contraseña para guardarla en la base de datos
        String sha256hex = Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}
