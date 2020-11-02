package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.PrescriptionModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class PrescriptionDao {

    private JdbcTemplate jdbcTemplate;

    public PrescriptionDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public ArrayList<PrescriptionModel> returnAllPrescriptionsByPatientId (int patientId){
        String query = "SELECT pre.prescription_id, med_his.diagnosis, per.first_name, per.first_surname, pre.prescription_date\n" +
                        "FROM prescription pre\n" +
                        "    JOIN medical_history med_his on pre.medical_history_id = med_his.medical_history_id\n" +
                        "        JOIN consult con on med_his.medical_history_id = con.medical_history_id\n" +
                        "            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id\n" +
                        "                JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id\n" +
                        "                    JOIN person per on doc.person_id = per.person_id\n" +
                        "                        JOIN patient pat on con.patient_id = pat.patient_id\n" +
                        "WHERE pat.patient_id = ? \n" +
                        "AND pre.status = 1\n" +
                        "AND med_his.status = 1\n" +
                        "AND doc_spec.status = 1\n" +
                        "AND doc.status = 1\n" +
                        "AND per.status = 1\n" +
                        "AND pat.status = 1;";
        ArrayList<PrescriptionModel> prescriptions = null;
        try{
            prescriptions = (ArrayList<PrescriptionModel>) jdbcTemplate.query(query, new Object[]{patientId},
                    new RowMapper<PrescriptionModel>() {
                        @Override
                        public PrescriptionModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PrescriptionModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDate(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return prescriptions;
    }

}
