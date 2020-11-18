package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.BL.SpecialtyBl;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.SpecialtyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/specialties")
@CrossOrigin(origins = "*")
public class SpecialtyController {

    private SpecialtyBl specialtyBl;
    private SecurityBl securityBl;

    @Autowired
    public SpecialtyController(SpecialtyBl specialtyBl, SecurityBl securityBl) {
        this.specialtyBl = specialtyBl;
        this.securityBl = securityBl;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<SpecialtyModel>> returnAllSpecialties (@RequestHeader("Authorization") String authorization){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.specialtyBl.returnAllSpecialties(), HttpStatus.OK);
    }

    @RequestMapping(
            path = "{specialtyId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnDoctorsBySpecialty (@RequestHeader("Authorization") String authorization,
                                                                         @PathVariable("specialtyId") Integer specialtyId){
        securityBl.validateToken(authorization);
        Map<String, Object> specialtiesList = specialtyBl.returnDoctorsBySpecialty(specialtyId);
        return new ResponseEntity (specialtiesList, HttpStatus.OK);
    }
}
