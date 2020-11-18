package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.LaboratoryBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.BL.SecurityBl;
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
    private LaboratoryBl laboratoryBl;
    private PrescriptionBl prescriptionBl;

    @Autowired
    public MedicalHistoryController(MedicalHistoryBl medicalHistoryBl, SecurityBl securityBl, LaboratoryBl laboratoryBl, PrescriptionBl prescriptionBl) {
        this.medicalHistoryBl = medicalHistoryBl;
        this.securityBl = securityBl;
        this.laboratoryBl = laboratoryBl;
        this.prescriptionBl = prescriptionBl;
    }


    @RequestMapping(
            value = "list/all/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<MedicalHistoryListModel>> returnAllMedicalHistory(@RequestHeader("Authorization") String authorization,
                                                                                              @PathVariable("userId") Integer userId){

        securityBl.validateToken(authorization);

        return new ResponseEntity<>(this.medicalHistoryBl.returnAllMedicalHistory(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "detail/{medicalHistoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnMedicalHistoryDetail(@RequestHeader("Authorization") String authorization,
                                                                                @PathVariable("medicalHistoryId") Integer medicalHistoryId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.medicalHistoryBl.returnMedicalHistoryDetail(medicalHistoryId), HttpStatus.OK);
    }
}
