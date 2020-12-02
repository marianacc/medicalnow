package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public MedicalDataModel returnMedicalDataByPatient (int userId){
        String query = "SELECT pat.weight, pat.height, pat.blood_group, pat.temperature, pat.pressure\n" +
                "FROM patient pat\n" +
                "    JOIN user usr on pat.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND pat.status = 1\n" +
                "AND usr.status = 1;";
        MedicalDataModel userData = null;
        try{
            userData = (MedicalDataModel) jdbcTemplate.queryForObject(query, new Object[]{userId},
                    new RowMapper<MedicalDataModel>() {
                        @Override
                        public MedicalDataModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new MedicalDataModel(resultSet.getDouble(1),
                                    resultSet.getDouble(2),
                                    resultSet.getString(3),
                                    resultSet.getDouble(4),
                                    resultSet.getString(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return userData;
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

    public PatientNameModel returnPatientNameByConsult(int consultId){
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

    public Integer returnPatientIdByConsult(int consultId){
        String query = "SELECT pat.patient_id\n" +
                "FROM patient pat\n" +
                "    JOIN medical_history mh on pat.patient_id = mh.patient_id\n" +
                "        JOIN consult con on mh.medical_history_id = con.medical_history_id\n" +
                "WHERE con.consult_id = ?\n" +
                "AND mh.status = 1\n" +
                "AND pat.status = 1\n" +
                "AND con.status = 2;";
        Integer patientId = null;
        try {
            patientId = jdbcTemplate.queryForObject(query, new Object[]{consultId}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return patientId;
    }

    public PatientNameModel returnPatientByMedicalHistory(int medicalHistoryId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname\n" +
                "FROM person per\n" +
                "    JOIN patient pat on per.person_id = pat.person_id\n" +
                "       JOIN medical_history mh on pat.patient_id = mh.patient_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND per.status = 1\n" +
                "AND mh.status = 1\n";
        PatientNameModel patientNameModel = null;
        try{
            patientNameModel = (PatientNameModel) jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId},
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
        return patientNameModel;
    }

    public Integer deleteAllergy(int patientId){
        String query = "UPDATE allergy\n" +
                "SET status = 0\n" +
                "WHERE patient_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId});
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public Integer addAllergy(int patientId, String description) {
        String query = "INSERT INTO allergy (patient_id, description, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId, description});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer deleteBackground(int patientId){
        String query = "UPDATE background\n" +
                "SET status = 0\n" +
                "WHERE patient_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId});
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public Integer addBackground(int patientId, String description) {
        String query = "INSERT INTO background (patient_id, description, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId, description});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public ArrayList<Object> returnBackground(int userId){
        String query = "SELECT b.description\n" +
                "FROM background b\n" +
                "    JOIN patient p on b.patient_id = p.patient_id\n" +
                "        JOIN user u on p.user_id = u.user_id\n" +
                "WHERE u.user_id = ?\n" +
                "AND b.status = 1\n" +
                "AND p.status = 1\n" +
                "AND u.status = 1;";
        ArrayList<Object> background = null;
        try{
            background = (ArrayList<Object>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<Object>() {
                        @Override
                        public String mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new String(resultSet.getString(1));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return background;
    }

    public ArrayList<Object> returnAllergies(int userId){
        String query = "SELECT a.description\n" +
                "FROM allergy a\n" +
                "    JOIN patient p on a.patient_id = p.patient_id\n" +
                "        JOIN user u on p.user_id = u.user_id\n" +
                "WHERE u.user_id = ?\n" +
                "AND a.status = 1\n" +
                "AND p.status = 1\n" +
                "AND u.status = 1;";
        ArrayList<Object> allergies = null;
        try{
            allergies = (ArrayList<Object>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<Object>() {
                        @Override
                        public String mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new String (resultSet.getString(1));
                        }
                    });
        } catch (Exception e){
            System.out.print(e);
            throw new RuntimeException();
        }
        return allergies;
    }
}