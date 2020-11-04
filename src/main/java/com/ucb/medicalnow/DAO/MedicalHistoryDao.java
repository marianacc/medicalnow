package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class MedicalHistoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicalHistoryDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer newMedicalHistoryByPatientId (int patientId){
        String query = "INSERT INTO medical_history (diagnosis, patient_id, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "VALUES ('', ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnMedicalHistoryIdByPatientId(int patientId){
        String query = "SELECT med.medical_history_id\n" +
                        "FROM medical_history med\n" +
                        "    JOIN patient pat on med.patient_id = pat.patient_id\n" +
                        "        JOIN user usr on pat.user_id = usr.user_id\n" +
                        "WHERE pat.patient_id = ?\n" +
                        "AND med.status = 1\n" +
                        "AND pat.status = 1\n" +
                        "AND usr.status = 1;";
        Integer medicalHistoryId = null;
        try {
            medicalHistoryId = jdbcTemplate.queryForObject(query, new Object[]{patientId}, Integer.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return medicalHistoryId;
    }


/*

    public Integer returnMaxMedicalHistoryId (){
        String query = "SELECT MAX(medical_history_id) from medical_history where medical_history.status = 1;";
        Integer medicalHistoryId = null;
        try {
            medicalHistoryId = jdbcTemplate.update(query, new Object[]{});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return medicalHistoryId;
    }
*/

}
