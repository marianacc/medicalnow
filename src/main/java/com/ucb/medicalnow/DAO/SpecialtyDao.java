package com.ucb.medicalnow.DAO;

import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.DoctorNameModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class SpecialtyDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SpecialtyDao(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public ArrayList<SpecialtyModel> returnAllSpecialties(){
        String query = "SELECT sp.specialty_id, sp.name, count(doc_sp.specialty_id), sp.image\n" +
                        "FROM specialty sp \n" +
                        "    JOIN doctor_specialty doc_sp on sp.specialty_id = doc_sp.specialty_id\n" +
                        "WHERE sp.status = 1\n" +
                        "and doc_sp.status = 1\n" +
                        "GROUP BY sp.name, doc_sp.specialty_id, sp.image;";
        ArrayList<SpecialtyModel> specialties = null;
        try{
            specialties = (ArrayList<SpecialtyModel>) jdbcTemplate.query(query, new Object[]{},
                    new RowMapper<SpecialtyModel>() {
                        @Override
                        public SpecialtyModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new SpecialtyModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getInt(3),
                                    resultSet.getString(4));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return specialties;
    }

    public ArrayList<DoctorSpecialtyModel> returnGeneralMedicineDoctors (){
        String query = "SELECT  doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname, avg(qua.qualification)\n" +
                        "FROM person per\n" +
                        "         JOIN doctor doc on per.person_id = doc.person_id\n" +
                        "         JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id\n" +
                        "         JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id\n" +
                        "         JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id\n" +
                        "WHERE doc.status = 1\n" +
                        "  AND per.status = 1\n" +
                        "  AND doc_spec.status = 1\n" +
                        "  AND qua.status = 1\n" +
                        "  AND spe.specialty_id = 4\n" +
                        "GROUP BY doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname;";

        ArrayList<DoctorSpecialtyModel> doctorSpecialty = null;
        try{
            doctorSpecialty = (ArrayList<DoctorSpecialtyModel>) jdbcTemplate.query(query, new Object[]{},
                    new RowMapper<DoctorSpecialtyModel>() {
                        @Override
                        public DoctorSpecialtyModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new DoctorSpecialtyModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDouble(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorSpecialty;
    }

    public ArrayList<DoctorSpecialtyModel> returnDoctorsBySpecialty (int specialtyId){
        String query = "SELECT  doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname, avg(qua.qualification)\n" +
                        "FROM person per\n" +
                        "         JOIN doctor doc on per.person_id = doc.person_id\n" +
                        "         JOIN doctor_specialty doc_spec on doc.doctor_id = doc_spec.doctor_id\n" +
                        "         JOIN qualification qua on doc_spec.doctor_specialty_id = qua.doctor_specialty_id\n" +
                        "         JOIN specialty spe on spe.specialty_id = doc_spec.specialty_id\n" +
                        "WHERE doc.status = 1\n" +
                        "  AND per.status = 1\n" +
                        "  AND doc_spec.status = 1\n" +
                        "  AND qua.status = 1\n" +
                        "  AND spe.specialty_id = ?\n" +
                        "GROUP BY doc_spec.doctor_specialty_id, per.first_name, per.first_surname, per.second_surname;";

        ArrayList<DoctorSpecialtyModel> doctorSpecialty = null;
        try{
            doctorSpecialty = (ArrayList<DoctorSpecialtyModel>) jdbcTemplate.query(query, new Object[]{specialtyId},
                    new RowMapper<DoctorSpecialtyModel>() {
                        @Override
                        public DoctorSpecialtyModel mapRow(ResultSet resultSet, int i) throws SQLException {
                            return new DoctorSpecialtyModel(resultSet.getInt(1),
                                    resultSet.getString(2),
                                    resultSet.getString(3),
                                    resultSet.getString(4),
                                    resultSet.getDouble(5));
                        }
                    });
        } catch (Exception e){
            throw new RuntimeException();
        }
        return doctorSpecialty;
    }

    public String returnSpecialtyNameBySpecialtyId (int specialtyId){
        String query = "SELECT name\n" +
                "FROM specialty\n" +
                "WHERE specialty_id = ?\n" +
                "AND status = 1;";
        String specialtyName = null;
        try {
            specialtyName = jdbcTemplate.queryForObject(query, new Object[]{specialtyId}, String.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return specialtyName;
    }
}
