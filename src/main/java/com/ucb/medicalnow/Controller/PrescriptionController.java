package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Model.*;
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
@RequestMapping("api/v1/prescription")
public class PrescriptionController {

    private PrescriptionBl prescriptionBl;
    private SecurityBl securityBl;

    @Autowired
    public PrescriptionController (PrescriptionBl prescriptionBl, SecurityBl securityBl) {
        this.prescriptionBl = prescriptionBl;
        this.securityBl = securityBl;
    }

    @RequestMapping(
            value = "patient/consults/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<PrescriptionListModel>> returnConsultsForPatient(@RequestHeader("Authorization") String authorization,
                                                                                               @PathVariable("userId") Integer userId){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.prescriptionBl.returnConsultsForPatient(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/consults/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<PrescriptionListModel>> returnConsultsForDoctor(@RequestHeader("Authorization") String authorization,
                                                                                               @PathVariable("userId") Integer userId){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.prescriptionBl.returnConsultsForDoctor(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "{consultId}/all",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnPrescriptions(@RequestHeader("Authorization") String authorization,
                                                                   @PathVariable("consultId") Integer consultId){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.prescriptionBl.returnAllPrescriptionsByConsult(consultId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "{prescriptionId}/detail",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnPrescriptionDetail(@RequestHeader("Authorization") String authorization,
                                                                        @PathVariable("prescriptionId") Integer prescriptionId){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.prescriptionBl.returnPrescriptionDetail(prescriptionId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "{consultId}/add/detail",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addPrescriptionDetail (@RequestHeader("Authorization") String authorization,
                                                                      @PathVariable("consultId") Integer consultId,
                                                                      @RequestBody PrescriptionModel prescriptionModel){
        securityBl.validateToken(authorization);

        Map<String, String> response = new HashMap<>();
        Boolean prescriptionUpdated = prescriptionBl.addPrescriptionDetail(consultId, prescriptionModel.getDescription(), prescriptionModel.getPrescription_detail());
        if(prescriptionUpdated){
            response.put("response", "Products added succesfully");
        } else {
            response.put("response", "Products not added");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
