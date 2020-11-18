package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.LaboratoryBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Model.LaboratoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/laboratories")
public class LaboratoryController {

    private SecurityBl securityBl;
    private LaboratoryBl laboratoryBl;

    @Autowired
    public LaboratoryController(SecurityBl securityBl, LaboratoryBl laboratoryBl) {
        this.securityBl = securityBl;
        this.laboratoryBl = laboratoryBl;
    }

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<LaboratoryModel>> returnAllLaboratoriesByPatient(@RequestHeader("Authorization") String authorization,
                                                                                    @PathVariable("userId") Integer userId){
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.laboratoryBl.returnAllLaboratoriesByUser(userId), HttpStatus.OK);
    }
}
