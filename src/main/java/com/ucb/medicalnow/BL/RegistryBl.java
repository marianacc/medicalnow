package com.ucb.medicalnow.BL;

import com.google.common.hash.Hashing;
import com.ucb.medicalnow.DAO.MedicalHistoryDao;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.DAO.PersonDao;
import com.ucb.medicalnow.DAO.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

@Service
public class RegistryBl {

    private UserDao userDao;
    private PersonDao personDao;
    private PatientDao patientDao;
    private MedicalHistoryDao medicalHistoryDao;

    @Value("${medicalnow.security.salt}")
    private String salt;

    public RegistryBl(UserDao userDao, PersonDao personDao, PatientDao patientDao, MedicalHistoryDao medicalHistoryDao) {
        this.userDao = userDao;
        this.personDao = personDao;
        this.patientDao = patientDao;
        this.medicalHistoryDao = medicalHistoryDao;
    }

    public Boolean addNewPatient (String firstName, String firstSurname, String secondSurname, Date birthDate,
                               String email, String password, String phoneNumber)
            throws ParseException {

        Boolean registryUpdated = null;
        // Se aplica el hash 256 a la contraseña para guardarla en la base de datos
        String sha256hex = Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();

        Integer personResponse = personDao.insertNewPerson(firstName, firstSurname, secondSurname, birthDate);
        if (personResponse > 0){
            Integer personId = personDao.returnMaxPersonId();
            Integer userResponse = userDao.insertNewUser(personId, email, sha256hex, phoneNumber);
            if (userResponse > 0){
                Integer userId = userDao.returnMaxUserId();
                Integer userRoleResponse = userDao.insertNewUserRole(userId);
                if (userRoleResponse > 0){
                    Integer patientResponse = patientDao.insertNewPatient(personId, userId);
                    if (patientResponse > 0){
                        registryUpdated = true;
                    } else {
                        registryUpdated = false;
                    }
                }
            }
        }
        return registryUpdated;
    }
}
