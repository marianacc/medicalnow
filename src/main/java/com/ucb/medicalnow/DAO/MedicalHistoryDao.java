package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.MedicalHistoryDateListModel;
import com.ucb.medicalnow.Model.MedicalHistoryDetailModel;
import com.ucb.medicalnow.Model.MedicalHistoryListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class MedicalHistoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MedicalHistoryDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer returnMedicalHistoryId(int patientId, int doctorSpecialtyId) {
        String query = "SELECT med.medical_history_id\n" +
                "FROM medical_history med\n" +
                "    JOIN patient pat on med.patient_id = pat.patient_id\n" +
                "        JOIN user usr on pat.user_id = usr.user_id\n" +
                "           JOIN doctor_specialty ds on med.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND ds.doctor_specialty_id = ?\n" +
                "AND med.status = 1\n" +
                "AND pat.status = 1\n" +
                "AND ds.status = 1;";
        Integer medicalHistoryId = null;
        try {
            medicalHistoryId = jdbcTemplate.queryForObject(query, new Object[]{patientId, doctorSpecialtyId}, Integer.class);
        } catch (Exception e) {
            medicalHistoryId = null;
        }
        return medicalHistoryId;
    }

    public Integer createMedicalHistory (int patientId, int doctorSpecialtyId){
        String query = "INSERT INTO medical_history (patient_id, doctor_specialty_id, status, tx_id, tx_username, tx_host, tx_date)\n" +
                        "VALUES (?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{patientId, doctorSpecialtyId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public ArrayList<MedicalHistoryListModel> returnAllMedicalHistory(int userId) {
        String query = "SELECT mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(c.tx_date)\n" +
                "FROM medical_history mh\n" +
                "    JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "        JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "            JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                JOIN person per on doc.person_id = per.person_id\n" +
                "                    JOIN consult c on mh.medical_history_id = c.medical_history_id\n" +
                "                        JOIN patient p on mh.patient_id = p.patient_id\n" +
                "                            JOIN user usr on p.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND mh.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND per.status = 1\n" +
                "AND p.status = 1\n" +
                "AND usr.status = 1\n" +
                "GROUP BY mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, spe.name;";
        ArrayList<MedicalHistoryListModel> medicalHistoryList = null;
        try {
            medicalHistoryList = (ArrayList<MedicalHistoryListModel>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<MedicalHistoryListModel>() {
                        @Override
                        public MedicalHistoryListModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new MedicalHistoryListModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getDate(6));
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return medicalHistoryList;
    }

    public ArrayList<MedicalHistoryDateListModel> returnConsultsByMedicalHistory(int medicalHistoryId) {
        String query = "SELECT con.consult_id, MIN(con.tx_date), con.status\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND mh.status = 1\n" +
                "GROUP BY con.consult_id";
        ArrayList<MedicalHistoryDateListModel> consults = null;
        try {
            consults = (ArrayList<MedicalHistoryDateListModel>) jdbcTemplate.query(query, new Object[]{medicalHistoryId},
                    new RowMapper<MedicalHistoryDateListModel>() {
                        @Override
                        public MedicalHistoryDateListModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new MedicalHistoryDateListModel(resultSet.getInt(1),
                                    resultSet.getDate(2),
                                    resultSet.getInt(3));
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return consults;
    }

    public MedicalHistoryDetailModel returnMedicalHistoryDetailByConsult(int consultId) {
        String query = "SELECT mh.medical_history_id, per.first_name, per.first_surname, per.second_surname, per.birthdate, usr.phone_number, usr.email\n" +
                "FROM person per\n" +
                "    JOIN user usr on per.person_id = usr.person_id\n" +
                "        JOIN patient pat on usr.user_id = pat.user_id\n" +
                "            JOIN medical_history mh on pat.patient_id = mh.patient_id\n" +
                "                JOIN consult con on mh.medical_history_id = con.medical_history_id\n" +
                "WHERE con.consult_id = ?\n" +
                "AND per.status = 1\n" +
                "AND usr.status = 1\n" +
                "AND pat.status = 1\n" +
                "AND mh.status = 1;";

        MedicalHistoryDetailModel medicalHistoryDetail = null;
        try {
            medicalHistoryDetail = (MedicalHistoryDetailModel) jdbcTemplate.queryForObject(query, new Object[]{consultId},
                    new RowMapper<MedicalHistoryDetailModel>() {
                        @Override
                        public MedicalHistoryDetailModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new MedicalHistoryDetailModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDate(5),
                                    resultSet.getString(6),
                                    resultSet.getString(7));
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return medicalHistoryDetail;
    }
}
