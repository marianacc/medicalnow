package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.BL.UserBl;
import com.ucb.medicalnow.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private UserBl userBl;
    private SecurityBl securityBl;

    @Autowired
    public UserController (UserBl userBl, SecurityBl securityBl) {
        this.userBl = userBl;
        this.securityBl = securityBl;
    }

    @RequestMapping(
            value = "patient/registry",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map <String, String>> addNewPatient(@RequestBody UserDataModel userDataModel) {
        Boolean registryUpdated = userBl.addNewPatient(userDataModel.getFirstName(), userDataModel.getFirstSurname(),
                userDataModel.getSecondSurname(), userDataModel.getBirthDate(), userDataModel.getEmail(),
                userDataModel.getPassword(), userDataModel.getPhoneNumber());

        Map <String, String> response = new HashMap();
        if(registryUpdated == true){
            response.put("Message","Patient added succesfully");
        }
        else{
            response.put("Message","Error. The patient wasn't added");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAvatarModel> returnAvatarByUser(@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("userId") Integer userId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.userBl.returnAvatarByUser(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "medical_data/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalDataModel> returnMedicalData (@RequestHeader("Authorization") String authorization,
                                                                  @PathVariable("userId") Integer userId) {
        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.userBl.returnMedicalData(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "medical_data/update/{userId}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateMedicalData (@RequestHeader("Authorization") String authorization,
                                                                  @RequestBody MedicalDataModel medicalDataModel,
                                                                  @PathVariable("userId") Integer userId) {
        securityBl.validateToken(authorization);

        Boolean registryUpdated = userBl.updateMedicalData(userId, medicalDataModel.getWeight(), medicalDataModel.getHeight(),
                medicalDataModel.getBloodGroup(), medicalDataModel.getTemperature(), medicalDataModel.getPressure());

        Map<String, String> response = new HashMap<>();
        if(registryUpdated){
            response.put("response", "Medical data updated");
        } else {
            response.put("response", "Error. Medical data not updated");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(
            value = "{userId}/config",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDataModel> returnUserData(@RequestHeader("Authorization") String authorization,
                                                                 @PathVariable("userId") Integer userId){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.userBl.returnUserInformation(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "{userId}/config/update",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> updateUserData(@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("userId") Integer userId,
                                                              @RequestBody UserDataModel userDataModel) {
        securityBl.validateToken(authorization);

        Boolean registryUpdated = userBl.updateDataByUser(userId, userDataModel.getFirstName(),
                userDataModel.getFirstSurname(), userDataModel.getSecondSurname(), userDataModel.getBirthDate(),
                userDataModel.getEmail(), userDataModel.getPassword(), userDataModel.getPhoneNumber());

        Map<String, String> response = new HashMap();
        if (registryUpdated == true) {
            response.put("Message", "User data updated succesfully");
        } else {
            response.put("Message", "Error. User data wasn't updated");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "{userId}/doctor/info",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorInfoModel returnDoctorInfo(@RequestHeader("Authorization") String authorization,
                                            @PathVariable("userId") Integer userId) {

        securityBl.validateToken(authorization);
        return this.userBl.returnDoctorInfo(userId);
    }

    @RequestMapping(
            value = "{userId}/update/doctor/info",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> returnDoctorInfo(@RequestHeader("Authorization") String authorization,
                                                                @PathVariable("userId") Integer userId,
                                                                @RequestBody DoctorInfoModel doctorInfoModel) {

        securityBl.validateToken(authorization);

        Boolean registryUpdated = userBl.updateDoctorInfo(doctorInfoModel.getPrice(), doctorInfoModel.getFromTime(), doctorInfoModel.getToTime(), userId);

        Map<String, String> response = new HashMap();
        if (registryUpdated == true) {
            response.put("Message", "Doctor info updated succesfully");
        } else {
            response.put("Message", "Error. Doctor info wasn't updated");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

