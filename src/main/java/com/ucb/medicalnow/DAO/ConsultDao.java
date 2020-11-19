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
public class ConsultDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsultDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer createNewConsult(int medicalHistoryId){
        String query = "INSERT INTO consult (medical_history_id, diagnosis, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, '', 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{medicalHistoryId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnConsultId(int medicalHistoryId) {
        String query = "SELECT con.consult_id\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1;";
        Integer consultId = null;
        try {
            consultId = jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId}, Integer.class);
        } catch (Exception e) {
            consultId = null;
        }
        return consultId;
    }

    public ArrayList<ConsultModel> returnAllConsultsByPatient (int userId){
        String query = "SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(con.tx_date), con.status\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "        JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "            JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "                JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                    JOIN person per on doc.person_id = per.person_id\n" +
                "                        JOIN patient pat on mh.patient_id = pat.patient_id\n" +
                "                            JOIN user usr on pat.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND per.status = 1\n" +
                "AND pat.status = 1\n" +
                "AND usr.status = 1\n" +
                "AND con.status != 0\n" +
                "GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date;";
        ArrayList<ConsultModel> consults = null;
        try{
            consults = (ArrayList<ConsultModel>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<ConsultModel>() {
                        @Override
                        public ConsultModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ConsultModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getDate(6),
                                    resultSet.getInt(7));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return consults;
    }

    public ArrayList<ConsultModel> returnAllConsultsByDoctor(int userId){
        String query = "SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, MIN(con.tx_date), con.status\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "        JOIN patient pat on mh.patient_id = pat.patient_id\n" +
                "            JOIN person per on pat.person_id = per.person_id\n" +
                "                JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "                    JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                        JOIN user usr on usr.user_id = doc.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "  AND con.status = 1\n" +
                "  AND mh.status = 1\n" +
                "  AND per.status = 1\n" +
                "  AND pat.status = 1\n" +
                "  AND ds.status = 1\n" +
                "  AND doc.status = 1\n" +
                "  AND usr.status = 1\n" +
                "GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, con.tx_date;";
        ArrayList<ConsultModel> consults = null;
        try{
            consults = (ArrayList<ConsultModel>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<ConsultModel>() {
                        @Override
                        public ConsultModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ConsultModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDate(5),
                                    resultSet.getInt(6));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return consults;
    }

    public Integer dischargeUserByConsultId(int consultId){
        String query = "UPDATE consult\n" +
                "SET status = 0\n" +
                "WHERE consult_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{consultId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer addDiagnosisByConsult(String diagnosis, int consultId){
        String query = "UPDATE consult\n" +
                "SET diagnosis = ?\n" +
                "WHERE consult_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{diagnosis, consultId});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public String returnDiagnosisByConsult(int consultId){
        String query = "SELECT diagnosis\n" +
                "FROM consult con\n" +
                "WHERE consult_id = ?;";
        String diagnosis = null;
        try {
            diagnosis = jdbcTemplate.queryForObject(query, new Object[]{consultId}, String.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return diagnosis;
    }

    public PaymentModel returnInfoByDoctorSpecialty (int doctorSpecialtyId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname, spe.name, avg(qua.qualification), ds.price\n" +
                "FROM person per\n" +
                "    JOIN doctor doc on per.person_id = doc.person_id\n" +
                "        JOIN doctor_specialty ds on doc.doctor_id = ds.doctor_id\n" +
                "            JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "                JOIN qualification qua on ds.doctor_specialty_id = qua.doctor_specialty_id\n" +
                "WHERE ds.doctor_specialty_id = ?\n" +
                "AND per.status = 1\n" +
                "and doc.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND qua.status = 1;";
        PaymentModel paymentInfo = null;
        try{
            paymentInfo = (PaymentModel) jdbcTemplate.queryForObject(query, new Object[]{doctorSpecialtyId},
                    new RowMapper<PaymentModel>() {
                        @Override
                        public PaymentModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PaymentModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDouble(5),
                                    resultSet.getInt(6));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return paymentInfo;
    }
}
