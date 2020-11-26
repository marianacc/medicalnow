package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.DoctorInfoModel;
import com.ucb.medicalnow.Model.DoctorNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

@Service
public class DoctorDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public DoctorDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public DoctorNameModel returnDoctorSpecialtyNameByConsult (int consultId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname, s.name, ds.doctor_specialty_id\n" +
                "FROM person per\n" +
                "    JOIN doctor doc on per.person_id = doc.person_id\n" +
                "        JOIN doctor_specialty ds on doc.doctor_id = ds.doctor_id\n" +
                "            JOIN specialty s on ds.specialty_id = s.specialty_id\n" +
                "                JOIN medical_history mh on ds.doctor_specialty_id = mh.doctor_specialty_id\n" +
                "                    JOIN consult c on mh.medical_history_id = c.medical_history_id\n" +
                "WHERE c.consult_id = ?\n" +
                "AND per.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND s.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND c.status = 1;";
        DoctorNameModel doctorAndSpecialtyName = null;
        try{
            doctorAndSpecialtyName = (DoctorNameModel) jdbcTemplate.queryForObject(query, new Object[]{consultId},
                    new RowMapper<DoctorNameModel>() {
                        @Override
                        public DoctorNameModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new DoctorNameModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getInt(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorAndSpecialtyName;
    }

    public DoctorNameModel returnDoctorSpecialtyNameByMedicalHistory(int medicalHistoryId){
        String query = "SELECT per.first_name, per.first_surname, per.second_surname, s.name\n" +
                "FROM person per\n" +
                "    JOIN doctor doc on per.person_id = doc.person_id\n" +
                "        JOIN doctor_specialty ds on doc.doctor_id = ds.doctor_id\n" +
                "            JOIN specialty s on ds.specialty_id = s.specialty_id\n" +
                "                JOIN medical_history mh on ds.doctor_specialty_id = mh.doctor_specialty_id\n" +
                "WHERE mh.medical_history_id = ?\n" +
                "AND per.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND s.status = 1\n" +
                "AND mh.status = 1\n";
        DoctorNameModel doctorSpecialtyName = null;
        try{
            doctorSpecialtyName = (DoctorNameModel) jdbcTemplate.queryForObject(query, new Object[]{medicalHistoryId},
                    new RowMapper<DoctorNameModel>() {
                        @Override
                        public DoctorNameModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new DoctorNameModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorSpecialtyName;
    }

    public DoctorInfoModel returnDoctorInfo(int userId){
        String query = "SELECT ds.price, ds.from_time, ds.to_time\n" +
                "FROM doctor_specialty ds\n" +
                "    JOIN doctor d on ds.doctor_id = d.doctor_id\n" +
                "        JOIN user u on d.user_id = u.user_id\n" +
                "WHERE u.user_id = ?\n" +
                "AND ds.status = 1\n" +
                "AND d.status = 1\n" +
                "AND u.status = 1;";
        DoctorInfoModel doctorInfo = null;
        try{
            doctorInfo = (DoctorInfoModel) jdbcTemplate.queryForObject(query, new Object[]{userId},
                    new RowMapper<DoctorInfoModel>() {
                        @Override
                        public DoctorInfoModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new DoctorInfoModel(resultSet.getInt(1),
                                    resultSet.getTime(2),
                                    resultSet.getTime(3));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorInfo;
    }

    public Integer returnDoctorIdByUser(int userId){
        String query = "SELECT d.doctor_id\n" +
                "FROM doctor d\n" +
                "    JOIN user u on d.user_id = u.user_id\n" +
                "WHERE u.user_id = ?\n" +
                "AND u.status = 1\n" +
                "AND d.status = 1;";
        Integer doctorId = null;
        try {
            doctorId = jdbcTemplate.queryForObject(query, new Object[]{userId}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorId;
    }

    public Integer updateDoctorInfo(int price, Time fromTime, Time toTime, int doctorId){
        String query = "UPDATE doctor_specialty\n" +
                "SET price = ?, from_time = ?, to_time = ?\n" +
                "WHERE doctor_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{price, fromTime, toTime, doctorId});
        } catch (Exception e){
            throw new RuntimeException();
        }
        return result;
    }
}
