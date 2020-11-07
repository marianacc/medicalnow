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

    /*public Long consultExists (int medicalHistoryId){
        String query = "";
        Long consultId = null;
        try {
            consultId = jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return consultId;
    }*/
}
