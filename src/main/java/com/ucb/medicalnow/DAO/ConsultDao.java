package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConsultDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsultDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer addConsult (int medicalHistoryId, int patientId, int doctorSpecialtyId, String message, Date consultDate){
        String query = "INSERT INTO consult (medical_history_id, patient_id, doctor_specialty_id, message, consult_date, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "VALUES (?, ?, ?, ?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{medicalHistoryId, patientId, doctorSpecialtyId, message, consultDate});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

}
