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

    public Long returnConsultId(int medicalHistoryId) {
        String query = "SELECT con.consult_id\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1;";
        Long consultId = null;
        try {
            consultId = jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId}, Long.class);
        } catch (Exception e) {
            consultId = null;
        }
        return consultId;
    }

    public Integer createNewConsult (int medicalHistoryId){
        String query = "INSERT INTO consult (medical_history_id, diagnosis, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, '', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{medicalHistoryId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}
