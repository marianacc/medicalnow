package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer findUserByEmailAndPassword(String email, String password){
        String query = "SELECT user_id FROM user WHERE email = ? AND UPPER(password) = UPPER(?)";
        Integer userId = null;
        try{
            userId = jdbcTemplate.queryForObject(query, new Object[]{ email, password }, Integer.class);
        }catch(Exception e){
            throw new RuntimeException();
        }
        return userId;
    }

    public String findRoleByUser(int userId){
        String query = "SELECT rol.role_name\n" +
                "FROM role rol\n" +
                "    JOIN user_role urol on rol.role_id = urol.role_id\n" +
                "        JOIN user usr on urol.user_id = usr.user_id\n" +
                "AND usr.user_id = ?\n" +
                "AND rol.status = 1\n" +
                "AND urol.status = 1;";
        String role = null;
        try {
            role = jdbcTemplate.queryForObject(query, new Object[]{userId}, String.class);
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return role;
    }

    public Integer addNewUser(int personId, String email, String password, String phoneNumber) {
        String query = "INSERT INTO user (person_id, email, password, phone_number, profile_picture, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "values (?, ?, ?, ?, 'shorturl.at/gluR7', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{personId, email, password, phoneNumber});
        } catch (Exception e) {
            System.out.print(e);
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

    public Integer addNewUserRole(int userId) {
        String query = "INSERT INTO user_role (user_id, role_id, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, 2, 1,  1, 'admin', 'localhost', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{userId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public UserAvatarModel returnUserNameByUser(int userId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname\n" +
                "FROM person per\n" +
                "    JOIN user usr on per.person_id = usr.person_id\n" +
                "WHERE usr.user_id = ? \n" +
                "AND per.status = 1\n" +
                "AND usr.status = 1;";
        UserAvatarModel userAvatar = null;
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

    public UserDataModel returnUserInformationByUser (int userId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname, per.birthdate, usr.email, usr.password, usr.phone_number\n" +
                "FROM person per\n" +
                "    JOIN user usr on per.person_id = usr.person_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND per.status = 1\n" +
                "AND usr.status = 1;";
        UserDataModel userData = null;
        try{
            userData = (UserDataModel) jdbcTemplate.queryForObject(query, new Object[]{userId},
                    new RowMapper<UserDataModel>() {
                        @Override
                        public UserDataModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new UserDataModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getDate(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6),
                                    resultSet.getString(7));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return userData;
    }

    public Integer updateUserData(String email, String password, String phoneNumber, int userId){
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

    //////////////

    public int findRoleIdByUserId(int userId){
        String query = "SELECT rol.role_id\n" +
                "FROM role rol\n" +
                "    JOIN user_role usr on rol.role_id = usr.role_id\n" +
                "        JOIN user u on usr.user_id = u.user_id\n" +
                "WHERE u.user_id = ?\n" +
                "AND rol.status = 1\n" +
                "AND usr.status = 1\n" +
                "AND u.status = 1;";
        Integer role = null;
        try {
            role = jdbcTemplate.queryForObject(query, new Object[]{userId}, Integer.class);
        }
        catch (Exception exception){
            throw new RuntimeException(exception);
        }
        return role;
    }
}



