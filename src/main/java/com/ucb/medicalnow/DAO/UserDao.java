package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList<String> findAllFeatureCodeByUserId(int userId){
        ArrayList<String> features =null;
        String query = "SELECT DISTINCT fea.feature_code\n" +
                        "FROM user usr\n" +
                        "JOIN user_role uro ON usr.user_id = uro.user_id\n" +
                        "JOIN role rle ON rle.role_id = uro.role_id\n" +
                        "JOIN role_feature rfe ON rfe.role_id = rle.role_id\n" +
                        "JOIN feature fea ON fea.feature_id = rfe.feature_id\n" +
                        "WHERE usr.user_id = ? \n" +
                        "AND usr.status = 1\n" +
                        "AND uro.status = 1\n" +
                        "AND rle.status = 1\n" +
                        "AND rfe.status = 1\n" +
                        "AND fea.status = 1";
        try {
            features = (ArrayList<String>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<String>(){
                        @Override
                        public String mapRow(ResultSet resultSet, int i) throws SQLException {
                            return resultSet.getString(1);
                        }
                    });
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }

        return features;
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

    public Integer registerNewUserRole (int userId) {
        String query = "INSERT INTO user_role ( user_id, role_id, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "VALUES (?, 2, 1,  1, 'admin', 'localhost', now());";

        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{userId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}