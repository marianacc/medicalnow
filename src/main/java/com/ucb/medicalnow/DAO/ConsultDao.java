package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.ConsultsDoctorModel;
import com.ucb.medicalnow.Model.ConsultsPatientModel;
import com.ucb.medicalnow.Model.DiagnosisModel;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class ConsultDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsultDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Long returnConsultId(int medicalHistoryId) {
        String query = "SELECT con.consult_id\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1;";
        Long consultId = null;
        try {
            consultId = jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId}, Long.class);
        } catch (Exception e) {
            consultId = null;
        }
        return consultId;
    }

    public Integer createNewConsult (int medicalHistoryId){
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

    public ArrayList<ConsultsPatientModel> returnAllConsultsByPatientId(int patientId){
        String query = "SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, MIN(con.tx_date)\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "        JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "            JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "                JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                    JOIN person per on doc.person_id = per.person_id\n" +
                "                        JOIN patient pat on mh.patient_id = pat.patient_id\n" +
                "WHERE pat.patient_id = ?\n" +
                "AND con.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND per.status = 1\n" +
                "AND pat.status = 1\n" +
                "GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date;";
        ArrayList<ConsultsPatientModel> consults = null;
        try{
            consults = (ArrayList<ConsultsPatientModel>) jdbcTemplate.query(query, new Object[]{patientId},
                    new RowMapper<ConsultsPatientModel>() {
                        @Override
                        public ConsultsPatientModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ConsultsPatientModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getString(5),
                                    resultSet.getDate(6));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return consults;
    }

    public ArrayList<ConsultsDoctorModel> returnAllConsultsByDoctorId (int doctorId){
        String query = "SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, MIN(con.tx_date)\n" +
                "FROM consult con\n" +
                "    JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "        JOIN patient pat on mh.patient_id = pat.patient_id\n" +
                "            JOIN person per on pat.person_id = per.person_id\n" +
                "                JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "                    JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "WHERE doc.doctor_id = ?\n" +
                "  AND con.status = 1\n" +
                "  AND mh.status = 1\n" +
                "  AND per.status = 1\n" +
                "  AND pat.status = 1\n" +
                "  AND ds.status = 1\n" +
                "  AND doc.status = 1\n" +
                "GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, con.tx_date;";
        ArrayList<ConsultsDoctorModel> consults = null;
        try{
            consults = (ArrayList<ConsultsDoctorModel>) jdbcTemplate.query(query, new Object[]{doctorId},
                    new RowMapper<ConsultsDoctorModel>() {
                        @Override
                        public ConsultsDoctorModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new ConsultsDoctorModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDate(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return consults;
    }

    public Integer dischargeUserByConsultId (int consultId){
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

    public DiagnosisModel returnDiagnosisByConsultId (int consultId){
        String query = "SELECT diagnosis\n" +
                "FROM consult con\n" +
                "WHERE consult_id = ?;";
        DiagnosisModel diagnosis = null;
        try {
            diagnosis = jdbcTemplate.queryForObject(query, new Object[]{consultId}, DiagnosisModel.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return diagnosis;
    }

    public Integer addDiagnosisByConsultId (String diagnosis, int consultId){
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
}
