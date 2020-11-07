package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.RegistryBl;
import com.ucb.medicalnow.BL.UserBl;
import com.ucb.medicalnow.Model.NewUserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController {

    private RegistryBl registryBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public PatientController (RegistryBl registryBl) {
        this.registryBl = registryBl;
    }

    @RequestMapping(
            value = "registry",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map <String, String>> newPatientRegistry (@RequestBody NewUserModel newUserModel) throws ParseException {
        Map <String, String> response = new HashMap();

        Boolean registryUpdated = registryBl.addNewPatient(newUserModel.getFirstName(), newUserModel.getFirstSurname(), newUserModel.getSecondSurname(),
                newUserModel.getBirthDate(), newUserModel.getEmail(), newUserModel.getPassword(), newUserModel.getPhoneNumber());

        if(registryUpdated == true){
            response.put("Message","Patient added succesfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else{
            response.put("Message","Error. The patient wasn't added");
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }
    }
}
