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
import java.text.SimpleDateFormat;
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

    public Boolean addNewPatient (String idNumber, String firstName, String firstSurname, String secondSurname,
                                    String birthDate, String city, String email, String password, String phoneNumber)
            throws ParseException {
        // Parsear la fecha guardada en String a Date
        Date birthdate = new SimpleDateFormat("yyyy/MM/dd").parse(birthDate);
        // Agregarle la Salt y aplicar el algoritmo hash256 a la contraseña para guardarla en la base de datos
        String sha256hex= Hashing.sha256()
                .hashString(password+salt, StandardCharsets.UTF_8)
                .toString();
        Boolean registryUpdated = null;
        Integer personResponse = personDao.addNewPerson(idNumber, firstName, firstSurname, secondSurname, birthdate, city);
        if (personResponse > 0){
            Integer personId = personDao.returnMaxPersonId();
            Integer userResponse = userDao.addNewUser(personId, email, sha256hex, phoneNumber);
            if (userResponse > 0){
                Integer userId = userDao.returnMaxUserId();
                Integer userRoleResponse = userDao.addNewUserRole(userId);
                if (userRoleResponse > 0){
                    Integer patientResponse = patientDao.addNewPatient(personId, userId);
                    if (patientResponse > 0){
                        Integer patientId = patientDao.returnMaxUserId();
                        Integer medicalHistoryResponse = medicalHistoryDao.newMedicalHistoryByPatientId(patientId);
                        if(medicalHistoryResponse> 0){
                            registryUpdated = true;
                        } else {
                            registryUpdated = false;
                        }
                    }
                }
            }
        }
        return registryUpdated;
    }
}
