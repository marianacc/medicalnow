package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.DoctorNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

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
}
