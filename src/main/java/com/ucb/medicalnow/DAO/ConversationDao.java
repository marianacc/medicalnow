package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.ConversationModel;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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

    public ArrayList<ConversationModel> returnConversationByConsultId (int consultId){
        String query = "SELECT rol.role_id, conv.message\n" +
                "FROM role rol\n" +
                "    JOIN user_role ur on rol.role_id = ur.role_id\n" +
                "        JOIN user usr on ur.user_id = usr.user_id\n" +
                "            JOIN patient p on usr.user_id = p.user_id\n" +
                "                JOIN medical_history mh on p.patient_id = mh.patient_id\n" +
                "                    JOIN consult con on mh.medical_history_id = con.medical_history_id\n" +
                "                        JOIN conversation conv on con.consult_id = conv.consult_id\n" +
                "WHERE usr.user_id = 1\n" +
                "AND con.consult_id = ?\n" +
                "AND rol.status = 1\n" +
                "AND ur.status = 1\n" +
                "AND usr.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND con.status = 1\n" +
                "AND conv.status = 1;";
        ArrayList<ConversationModel> conversation = null;
        try{
            conversation = (ArrayList<ConversationModel>) jdbcTemplate.query(query, new Object[]{consultId},
                    new RowMapper<ConversationModel>() {
                        @Override
                        public ConversationModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ConversationModel(resultSet.getInt(1),
                                    resultSet.getString(2));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return conversation;
    }
}
