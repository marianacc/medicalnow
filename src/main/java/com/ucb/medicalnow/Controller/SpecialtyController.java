package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.SpecialtyBl;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/specialties")
@CrossOrigin(origins = "*")
public class SpecialtyController {

    private SpecialtyBl specialtyBl;

    @Autowired
    public SpecialtyController(SpecialtyBl specialtyBl) { this.specialtyBl = specialtyBl; }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ArrayList<SpecialtyModel>> returnAllSpecialties (){
        return new ResponseEntity<>(this.specialtyBl.returnAllSpecialties(), HttpStatus.OK);
    }

    @RequestMapping(
            path = "/{specialtyId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<ArrayList<DoctorSpecialtyModel>> returnDoctorsBySpecialty (@PathVariable("specialtyId") Integer specialtyId){
        return new ResponseEntity<>(this.specialtyBl.returnDoctorsBySpecialty(specialtyId), HttpStatus.OK);
    }

}
