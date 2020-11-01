package com.ucb.medicalnow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDao (JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public Integer registerNewPerson (String idNumber, String firstName, String firstSurname, String secondSurname, Date birthDate, String city) {
        String query = "INSERT INTO person (id_number, first_name, first_surname, second_surname, birthdate, city, status, tx_id, tx_username, tx_host, tx_date)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, 1, 0, 'root', '127.0.0.1', now());";
        Integer result = null;
        try {
            result = jdbcTemplate.update(query, new Object[]{idNumber, firstName, firstSurname, secondSurname, birthDate, city});
            System.out.print(result);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return result;
    }

    public Integer returnMaxPersonId (){
        String query = "SELECT MAX(person_id) FROM patient WHERE status = 1;";
        Integer personId = null;
        try {
            personId = jdbcTemplate.queryForObject(query, new Object[]{}, Integer.class);
        } catch (Exception e){
            throw new RuntimeException();
        }
        return personId;
    }
}

