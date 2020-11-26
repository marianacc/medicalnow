package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.ChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

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
}
