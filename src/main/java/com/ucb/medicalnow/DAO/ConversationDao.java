package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;

@Service
public class ConversationDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConversationDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer addMessageToConversation (int consultId, String message, int userId){
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
