package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class PatientDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer registerNewPatient (int personId, int userId) {
        String query = "INSERT INTO patient (person_id, user_id, gender, height, weight, blood_group, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "VALUES (?, ?, 'N/N', 0, 0, 'N/N', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{personId, userId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnMaxUserId (){
        String query = "SELECT MAX(patient_id) FROM patient WHERE status = 1;";
        Integer patientId = null;
        try {
            patientId = jdbcTemplate.queryForObject(query, new Object[]{}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return patientId;
    }
}