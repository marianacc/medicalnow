package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
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
@RequestMapping("api/v1/consult")
public class ConsultController {

    private ConsultBl consultBl;
    private SecurityBl securityBl;
    private MedicalHistoryBl medicalHistoryBl;

    @Autowired
    public ConsultController(ConsultBl consultBl, SecurityBl securityBl, MedicalHistoryBl medicalHistoryBl) {
        this.consultBl = consultBl;
        this.securityBl = securityBl;
        this.medicalHistoryBl = medicalHistoryBl;
    }

    @RequestMapping(
            value = "{doctorSpecialtyId}/payment/info",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentModel> returnInfoForPayment (@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("doctorSpecialtyId") Integer doctorSpecialtyId) {
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(consultBl.returnInfoForPayment(doctorSpecialtyId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "new/{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createConsult (@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("userId") Integer userId,
                                                              @RequestBody DoctorSpecialtyIdModel doctorSpecialtyIdModel) {
        securityBl.validateToken(authorization);

        Integer medicalHistoryId = medicalHistoryBl.searchMedicalHistory(userId, doctorSpecialtyIdModel.getDoctorSpecialtyId());
        Integer consultId = consultBl.createNewConsult(medicalHistoryId);
        Map<String, Object> result = new HashMap<>();
        if (consultId!=null){
            result.put("message", "Consult created succesfully");
            result.put("consultId", consultId);
        } else {
            result.put("message", "Consult not created");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(
            value = "patient/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ConsultModel>> returnAllConsultsByPatient(@RequestHeader("Authorization") String authorization,
                                                                              @PathVariable("userId") Integer userId) {
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.consultBl.returnAllConsultsByPatient(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ConsultModel>> returnAllConsultsByDoctor(@RequestHeader("Authorization") String authorization,
                                                                             @PathVariable("userId") Integer userId) {

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.consultBl.returnAllConsultsByDoctor(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "discharge/{consultId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> dischargeUser(@RequestHeader("Authorization") String authorization,
                                                             @PathVariable("consultId") Integer consultId) {
        securityBl.validateToken(authorization);

        Map<String, String> response = new HashMap();
        Boolean registryUpdated = consultBl.dischargeUserByConsultId(consultId);
        if (registryUpdated){
            response.put("message", "Patient discharged succesfully");
        } else {
            response.put("message", "Patient not discharged");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "add/diagnosis/{consultId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addDiagnosis(@RequestHeader("Authorization") String authorization,
                                                            @PathVariable("consultId") Integer consultId,
                                                            @RequestBody DiagnosisModel diagnosisModel) {
        securityBl.validateToken(authorization);

        Map<String, String> response = new HashMap();
        Boolean consultResponse = consultBl.addDiagnosisByConsult(diagnosisModel.getDiagnosis(), consultId);
        if (consultResponse){
            response.put("message", "Diagnosis added succesfully");
        } else {
            response.put("message", "Diagnosis not added");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "diagnosis/{consultId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> showDiagnosis(@RequestHeader("Authorization") String authorization,
                                                @PathVariable("consultId") Integer consultId) {
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(consultBl.returnDiagnosisByConsult(consultId), HttpStatus.OK);
    }
}
