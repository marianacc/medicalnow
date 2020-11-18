package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.LaboratoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class LaboratoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LaboratoryDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public ArrayList<LaboratoryModel> returnAllLaboratoriesByUser(int userId){
        String query = "SELECT lab.laboratory_id, lab.name, per.first_name, per.first_surname, per.second_surname, spe.name, DATE(lab.tx_date)\n" +
                "FROM laboratory lab\n" +
                "    JOIN consult con on lab.consult_id = con.consult_id\n" +
                "        JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "            JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "                JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "                    JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                        JOIN person per on doc.person_id = per.person_id\n" +
                "                            JOIN patient p on mh.patient_id = p.patient_id\n" +
                "                                JOIN user usr on p.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND lab.status = 1\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND per.status = 1\n" +
                "AND usr.status = 1;";
        ArrayList<LaboratoryModel> laboratories = null;
        try{
            laboratories = (ArrayList<LaboratoryModel>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<LaboratoryModel>() {
                        @Override
                        public LaboratoryModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new LaboratoryModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getString(6),
                                    resultSet.getDate(7));
                        }
                    });
        } catch (Exception e){
            laboratories = null;
            throw new RuntimeException();
        }
        return laboratories;
    }
}
