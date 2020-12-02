package com.ucb.medicalnow.BL;

import com.google.common.hash.Hashing;
import com.ucb.medicalnow.DAO.DoctorDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import com.ucb.medicalnow.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

@Service
public class UserBl {

    private UserDao userDao;
    private PersonDao personDao;
    private PatientDao patientDao;
    private DoctorDao doctorDao;

    @Value("${medicalnow.security.salt}")
    private String salt;

    @Autowired
    public UserBl (UserDao userDao, PersonDao personDao, PatientDao patientDao, DoctorDao doctorDao) {
        this.userDao = userDao;
        this.personDao = personDao;
        this.patientDao = patientDao;
        this.doctorDao = doctorDao;
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

    public DoctorInfoModel returnDoctorInfo(int userId){
        return this.doctorDao.returnDoctorInfo(userId);
    }

    public Boolean updateDoctorInfo(int price, Time fromTime, Time toTime, int userId){
        Integer doctorId = doctorDao.returnDoctorIdByUser(userId);
        Integer doctorInfoResponse = doctorDao.updateDoctorInfo(price, fromTime, toTime, doctorId);
        Boolean registryUpdated = null;
        if (doctorInfoResponse>0){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public Boolean updateAllergy(int userId, ArrayList<String> description){
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        Integer deleteAllergyResponse = patientDao.deleteAllergy(patientId);
        Boolean registryUpdated = null;
        if(deleteAllergyResponse>=0 || deleteAllergyResponse==null){
            for(int i=0; i<description.size(); i++){
                Integer addAllergyResponse = patientDao.addAllergy(patientId, description.get(i));
                if (addAllergyResponse>0){
                    registryUpdated = true;
                } else {
                    registryUpdated = false;
                }
            }
        }
        return registryUpdated;
    }

    public Boolean updateBackground(int userId, ArrayList<String> description){
        Integer patientId = patientDao.returnPatientIdByUser(userId);
        Integer deleteBackgroundResponse = patientDao.deleteBackground(patientId);
        Boolean registryUpdated = null;
        if(deleteBackgroundResponse>=0 || deleteBackgroundResponse==null){
            for(int i=0; i<description.size(); i++){
                Integer addBackgroundResponse = patientDao.addBackground(patientId, description.get(i));
                if (addBackgroundResponse>0){
                    registryUpdated = true;
                } else {
                    registryUpdated = false;
                }
            }
        }
        return registryUpdated;
    }

    public ArrayList<String> returnAllergies(int userId){
        return this.patientDao.returnAllergies(userId);
    }

    public ArrayList<String> returnBackground(int userId){
        return this.patientDao.returnBackground(userId);
    }
}
