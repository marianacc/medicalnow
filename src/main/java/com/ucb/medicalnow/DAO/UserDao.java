package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserConfigurationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Array;
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
        String query = "SELECT DISTINCT fea.feature_code\n" +
                        "FROM user usr\n" +
                        "   JOIN user_role uro ON usr.user_id = uro.user_id\n" +
                        "       JOIN role rle ON rle.role_id = uro.role_id\n" +
                        "           JOIN role_feature rfe ON rfe.role_id = rle.role_id\n" +
                        "               JOIN feature fea ON fea.feature_id = rfe.feature_id\n" +
                        "WHERE usr.user_id = ? \n" +
                        "AND usr.status = 1\n" +
                        "AND uro.status = 1\n" +
                        "AND rle.status = 1\n" +
                        "AND rfe.status = 1\n" +
                        "AND fea.status = 1";
        ArrayList<String> features =null;
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

    public Integer addNewUser (int personId, String email, String password, String phoneNumber) {
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

    public Integer addNewUserRole (int userId) {
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

    public UserAvatarModel returnUserNameByUserId (int userId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname\n" +
                        "FROM person per\n" +
                        "    JOIN user usr on per.person_id = usr.person_id\n" +
                        "WHERE usr.user_id = ? \n" +
                        "AND per.status = 1\n" +
                        "AND usr.status = 1;";
        UserAvatarModel userAvatar= null;
        try {
            userAvatar = (UserAvatarModel) jdbcTemplate.queryForObject(query, new Object[]{userId},
                    new RowMapper<UserAvatarModel>(){
                        @Override
                        public UserAvatarModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new UserAvatarModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3));
                        }
                    });
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return userAvatar;
    }

    public UserConfigurationModel returnUserConfigurationByUserId (int userId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname, usr.phone_number, per.birthdate, pat.weight, pat.height, per.city, usr.email, usr.password\n" +
                        "FROM person per\n" +
                        "    JOIN user usr on per.person_id = usr.person_id\n" +
                        "        JOIN patient pat on usr.user_id = pat.user_id\n" +
                        "WHERE usr.user_id = ? \n" +
                        "AND per.status = 1\n" +
                        "AND usr.status = 1\n" +
                        "AND pat.status = 1;";

        UserConfigurationModel information = null;
        try{
            information = (UserConfigurationModel) jdbcTemplate.queryForObject(query, new Object[]{userId},
                    new RowMapper<UserConfigurationModel>() {
                        @Override
                        public UserConfigurationModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new UserConfigurationModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDate(5),
                                    resultSet.getDouble(6),
                                    resultSet.getDouble(7),
                                    resultSet.getString(8),
                                    resultSet.getString(9));
                        }
                    });
        } catch (Exception e){
            System.out.print(e);
            throw new RuntimeException();
        }
        return information;
    }

    public Integer updateUser (String email, String password, String phoneNumber, int userId){
        String query="UPDATE user\n" +
                    "SET email = ?, password = ?, phone_number = ?\n" +
                    "WHERE user_id = ?\n" +
                    "AND user.status = 1;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{email, password, phoneNumber, userId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }
}



