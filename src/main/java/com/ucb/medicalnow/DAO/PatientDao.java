package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.DoctorNameModel;
import com.ucb.medicalnow.Model.PatientNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PatientDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PatientDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer addNewPatient (int personId, int userId) {
        String query = "INSERT INTO patient (person_id, user_id, gender, height, weight, blood_group, temperature, pressure, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, '-', '0.0', '0.0', '-', '0.0', '0.0', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{personId, userId});
        } catch (Exception e) {
            System.out.print(e);
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnPatientIdByUser(int userId) {
        String query = "SELECT pat.patient_id\n" +
                "FROM patient pat\n" +
                "         JOIN user usr on pat.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND pat.status = 1\n" +
                "AND usr.status = 1;";
        Integer patientId = null;
        try {
            patientId = jdbcTemplate.queryForObject(query, new Object[]{userId}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return patientId;
    }

    public Integer updateMedicalDataByPatient(Double weight, Double height, String bloodGroup, Double temperature, String pressure, int patientId){
        String query = "UPDATE patient\n" +
                "SET weight = ?, height = ?, blood_group = ?, temperature = ?, pressure = ?\n" +
                "WHERE patient_id = ?\n" +
                "AND status = 1;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{weight, height, bloodGroup, temperature, pressure, patientId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    ///////
    public PatientNameModel returnPatientNameByConsultId (int consultId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname\n" +
                "FROM person per\n" +
                "    JOIN user usr on per.person_id = usr.person_id\n" +
                "        JOIN patient pat on per.person_id = pat.person_id\n" +
                "            JOIN medical_history mh on pat.patient_id = mh.patient_id\n" +
                "                JOIN consult con on mh.medical_history_id = con.medical_history_id\n" +
                "WHERE con.consult_id = ?\n" +
                "AND per.status = 1\n" +
                "AND usr.status = 1\n" +
                "AND pat.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND con.status = 1;";
        PatientNameModel patientName = null;
        try{
            patientName = (PatientNameModel) jdbcTemplate.queryForObject(query, new Object[]{consultId},
                    new RowMapper<PatientNameModel>() {
                        @Override
                        public PatientNameModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PatientNameModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return patientName;
    }
}