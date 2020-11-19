package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.PrescriptionDetailModel;
import com.ucb.medicalnow.Model.PrescriptionListModel;
import com.ucb.medicalnow.Model.PrescriptionDateListModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class PrescriptionDao {

    private JdbcTemplate jdbcTemplate;

    public PrescriptionDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public ArrayList<PrescriptionListModel> returnAllConsultsWithPrescriptions(int userId){
        String query = "SELECT con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date\n" +
                "FROM consult con\n" +
                "    JOIN prescription pre on con.consult_id = pre.consult_id\n" +
                "        JOIN medical_history mh on con.medical_history_id = mh.medical_history_id\n" +
                "            JOIN doctor_specialty ds on mh.doctor_specialty_id = ds.doctor_specialty_id\n" +
                "                JOIN specialty spe on ds.specialty_id = spe.specialty_id\n" +
                "                    JOIN doctor doc on ds.doctor_id = doc.doctor_id\n" +
                "                        JOIN person per on doc.person_id = per.person_id\n" +
                "                            JOIN patient pat on mh.patient_id = pat.patient_id\n" +
                "                                JOIN user usr on pat.user_id = usr.user_id\n" +
                "WHERE usr.user_id = ?\n" +
                "AND con.status = 1\n" +
                "AND pre.status = 1\n" +
                "AND mh.status = 1\n" +
                "AND ds.status = 1\n" +
                "AND spe.status = 1\n" +
                "AND doc.status = 1\n" +
                "AND per.status = 1\n" +
                "GROUP BY con.consult_id, per.first_name, per.first_surname, per.second_surname, spe.name, con.tx_date;";
        ArrayList<PrescriptionListModel> consultsList = null;
        try{
            consultsList = (ArrayList<PrescriptionListModel>) jdbcTemplate.query(query, new Object[]{userId},
                    new RowMapper<PrescriptionListModel>() {
                        @Override
                        public PrescriptionListModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PrescriptionListModel(resultSet.getInt(1),
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
        return consultsList;
    }

    public ArrayList<PrescriptionDateListModel> returnAllPrescriptionsByConsult(int consultId){
        String query = "SELECT pre.prescription_id, con.tx_date\n" +
                "FROM prescription pre\n" +
                "    JOIN consult con on pre.consult_id = con.consult_id\n" +
                "WHERE con.consult_id = ?\n" +
                "AND pre.status = 1\n" +
                "AND con.status = 1;";
        ArrayList<PrescriptionDateListModel> prescriptions = null;
        try{
            prescriptions = (ArrayList<PrescriptionDateListModel>) jdbcTemplate.query(query, new Object[]{consultId},
                    new RowMapper<PrescriptionDateListModel>() {
                        @Override
                        public PrescriptionDateListModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PrescriptionDateListModel(resultSet.getInt(1),
                                    resultSet.getDate(2));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return prescriptions;
    }

    public ArrayList<PrescriptionDetailModel> returnPrescriptionDetail(int prescriptionId){
        String query = "SELECT pro.product_name, pro.product_detail, pro.product_quantity\n" +
                "FROM product pro\n" +
                "    JOIN prescription pre on pro.prescription_id = pre.prescription_id\n" +
                "WHERE pre.prescription_id = ?\n" +
                "AND pre.status = 1\n" +
                "AND pro.status = 1;";
        ArrayList<PrescriptionDetailModel> prescriptionDetail = null;
        try{
            prescriptionDetail = (ArrayList<PrescriptionDetailModel>) jdbcTemplate.query(query, new Object[]{prescriptionId},
                    new RowMapper<PrescriptionDetailModel>() {
                        @Override
                        public PrescriptionDetailModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new PrescriptionDetailModel(resultSet.getString(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return prescriptionDetail;
    }

    public Integer addNewPrescription (int consultId, String description){
        String query = "INSERT INTO prescription (consult_id, description, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{consultId, description});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnMaxPrescriptionId (int consultId){
        String query = "SELECT MAX(prescription_id)\n" +
                "FROM prescription\n" +
                "WHERE consult_id = ?;";
        Integer result = null;
        try {
            result = jdbcTemplate.queryForObject(query, new Object[]{consultId}, Integer.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer insertNewProductsByPrescriptionId (int prescriptionId, String productName, String productDetail, String productQtty){
        String query = "INSERT INTO product (prescription_id, product_name, product_detail, product_quantity, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, ?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{prescriptionId, productName, productDetail, productQtty});
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public String returnDescriptionByPrescription(int prescriptionId){
        String query = "SELECT description\n" +
                "FROM prescription\n" +
                "WHERE prescription_id = ?\n" +
                "AND status = 1;";
        String description = null;
        try {
            description = jdbcTemplate.queryForObject(query, new Object[]{prescriptionId}, String.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return description;
    }

    public ArrayList<Integer> returnPrescriptionIdByConsult(int consultId){
        String query = "SELECT pre.prescription_id\n" +
                "FROM prescription pre\n" +
                "    JOIN consult c on pre.consult_id = c.consult_id\n" +
                "WHERE c.consult_id = ?\n" +
                "AND pre.status = 1;";
        ArrayList<Integer> prescriptionIdList = null;
        try {
            prescriptionIdList = (ArrayList<Integer>) jdbcTemplate.query(query, new Object[]{consultId},
                    new RowMapper<Integer>() {
                        @Override
                        public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new Integer(resultSet.getString(1));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return prescriptionIdList;
    }
}