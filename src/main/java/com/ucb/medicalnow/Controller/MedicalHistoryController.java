package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.LaboratoryBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Model.MedicalHistoryDateListModel;
import com.ucb.medicalnow.Model.MedicalHistoryListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("api/v1/medical_history")
public class MedicalHistoryController {

    private MedicalHistoryBl medicalHistoryBl;
    private SecurityBl securityBl;

    @Autowired
    public MedicalHistoryController(MedicalHistoryBl medicalHistoryBl, SecurityBl securityBl) {
        this.medicalHistoryBl = medicalHistoryBl;
        this.securityBl = securityBl;
    }

    @RequestMapping(
            value = "patient/list/all/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<MedicalHistoryListModel>> returnAllMedicalHistoryForPatient(@RequestHeader("Authorization") String authorization,
                                                                                            @PathVariable("userId") Integer userId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnAllMedicalHistoryForPatient(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/list/all/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<MedicalHistoryListModel>> returnAllMedicalHistoryForDoctor(@RequestHeader("Authorization") String authorization,
                                                                                               @PathVariable("userId") Integer userId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnAllMedicalHistoryForDoctor(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "patient/{medicalHistoryId}/all/consults",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnAllConsultsByMedicalHistoryForPatient(@RequestHeader("Authorization") String authorization,
                                                                                            @PathVariable("medicalHistoryId") Integer medicalHistoryId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnConsultsByMedicalHistoryForPatient(medicalHistoryId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/{medicalHistoryId}/all/consults",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnAllConsultsByMedicalHistoryForDoctor(@RequestHeader("Authorization") String authorization,
                                                                                          @PathVariable("medicalHistoryId") Integer medicalHistoryId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnConsultsByMedicalHistoryForDoctor(medicalHistoryId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "{consultId}/detail",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnMedicalHistoryDetailByConsult(@RequestHeader("Authorization") String authorization,
                                                                                   @PathVariable("consultId") Integer consultId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnMedicalHistoryDetailByConsult(consultId), HttpStatus.OK);
    }
}
