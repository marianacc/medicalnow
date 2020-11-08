package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DoctorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer returnDoctorIdByUserId (int userId) {
        String query = "SELECT doc.doctor_id\n" +
                "FROM doctor doc\n" +
                "        JOIN user usr on doc.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND doc.status = 1\n" +
                "AND usr.status = 1;";
        Integer doctorId = null;
        try {
            doctorId = jdbcTemplate.queryForObject(query, new Object[]{userId}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorId;
    }
}
