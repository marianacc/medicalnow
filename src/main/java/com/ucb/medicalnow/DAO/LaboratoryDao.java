package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.LaboratoryOrderModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
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

    public ArrayList<LaboratoryOrderModel> returnAllLaboratoriesByPatient (int patientId){
        String query = "SELECT lab.laboratory_exams_id, lab.laboratory_name, per.first_name, per.first_surname, spec.specialty_name, lab.lab_order_date\n" +
                        "FROM laboratory lab\n" +
                        "    JOIN medical_history med_his on lab.medical_history_id = med_his.medical_history_id\n" +
                        "        JOIN consult con on med_his.medical_history_id = con.medical_history_id\n" +
                        "            JOIN doctor_specialty doc_spec on con.doctor_specialty_id = doc_spec.doctor_specialty_id\n" +
                        "                JOIN specialty spec on doc_spec.specialty_id = spec.specialty_id\n" +
                        "                    JOIN doctor doc on doc_spec.doctor_id = doc.doctor_id\n" +
                        "                        JOIN person per on doc.person_id = per.person_id\n" +
                        "                            JOIN patient pat on con.patient_id = pat.patient_id\n" +
                        "WHERE pat.patient_id = ?\n" +
                        "AND lab.status = 1\n" +
                        "AND med_his.status = 1\n" +
                        "AND doc_spec.status = 1\n" +
                        "AND spec.status = 1\n" +
                        "AND doc.status = 1\n" +
                        "AND per.status = 1\n" +
                        "AND pat.status = 1;";

        ArrayList<LaboratoryOrderModel> laboratories = null;
        try{
            laboratories = (ArrayList<LaboratoryOrderModel>) jdbcTemplate.query(query, new Object[]{patientId},
                    new RowMapper<LaboratoryOrderModel>() {
                        @Override
                        public LaboratoryOrderModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new LaboratoryOrderModel(resultSet.getString(1),
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
        return laboratories;
    }
}
