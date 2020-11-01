package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.RegistryBl;
import com.ucb.medicalnow.Model.NewUserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private RegistryBl registryBl;

    public UserController (RegistryBl registryBl) { this.registryBl = registryBl; }

    @RequestMapping(
            value = "registry",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)

    public ResponseEntity<Map<String, String>> newPatientRegistry (@RequestBody NewUserModel newUserModel) throws ParseException {
        Map <String, String> response = new HashMap();
        Boolean registryUpdated = registryBl.newPatientRegistry(newUserModel.getIdNumber(), newUserModel.getFirstName(),
                newUserModel.getFirstSurname(), newUserModel.getSecondSurname(), newUserModel.getBirthDate(),
                newUserModel.getCity(), newUserModel.getEmail(), newUserModel.getPassword(), newUserModel.getPhoneNumber());

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
