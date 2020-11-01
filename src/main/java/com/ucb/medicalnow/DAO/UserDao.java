package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer findUserByEmailAndPassword (String email, String password){
        String query = "SELECT user_id FROM user WHERE email = ? AND UPPER(password) = UPPER(?)";
        Integer userId = null;
        try{
            userId = jdbcTemplate.queryForObject(query, new Object[]{ email, password }, Integer.class);
        }catch(Exception e){
            throw new RuntimeException();
        }
        return userId;
    }

    public Integer registerNewUser (int personId, String email, String password, String phoneNumber) {
        String query = "INSERT INTO user (person_id, email, password, phone_number, user_image, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "values (?, ?, ?, ?, 'http://dummyimage.com/227x147.png/ff4444/ffffff', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{personId, email, password, phoneNumber});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnMaxUserId (){
        String query = "SELECT MAX(user_id) FROM user WHERE status = 1;";
        Integer userId = null;
        try {
            userId = jdbcTemplate.queryForObject(query, new Object[]{}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return userId;
    }
}