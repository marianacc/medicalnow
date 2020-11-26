package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Interfaces.ImageRepository;
import com.ucb.medicalnow.Model.ChatModel;
import com.ucb.medicalnow.Model.DiagnosisModel;
import com.ucb.medicalnow.Model.ImageModelTry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ChatDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ChatDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public ArrayList<ChatModel> returnChatByConsult(int consultId){
        String query = "SELECT conv.tx_id, conv.message\n" +
                "FROM conversation conv\n" +
                "    JOIN consult cons on conv.consult_id = cons.consult_id\n" +
                "WHERE conv.consult_id = ?\n" +
                "AND conv.status = 1\n" +
                "AND cons.status = 1;";
        ArrayList<ChatModel> conversation = null;
        try{
            conversation = (ArrayList<ChatModel>) jdbcTemplate.query(query, new Object[]{consultId},
                    new RowMapper<ChatModel>() {
                        @Override
                        public ChatModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ChatModel(resultSet.getInt(1),
                                    resultSet.getString(2));
                        }
                    });
        } catch (Exception e){
            conversation = null;
        }
        return conversation;
    }

    public Integer addMessageToChat(int consultId, String message, int userId){
        String query = "INSERT INTO conversation (consult_id, message, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, 1, ?, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{consultId, message, userId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer addImage(String originalFileName, String contentType, byte[] imageCompressed){
        String query = "INSERT INTO resource (consult_id, name, type, pic_byte, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (0, ?, ?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{originalFileName, contentType, imageCompressed});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public ImageModelTry findImageByName(String imageName){
        String query = "SELECT resource_id, consult_id, type, pic_byte\n" +
                "FROM resource\n" +
                "WHERE name = ?\n" +
                "AND status = 1;";
        ImageModelTry image = null;
        try{
            image = (ImageModelTry) jdbcTemplate.queryForObject(query, new Object[]{imageName},
                    new RowMapper<ImageModelTry>() {
                        @Override
                        public ImageModelTry mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ImageModelTry(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getBytes(4));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return image;
    }
}
