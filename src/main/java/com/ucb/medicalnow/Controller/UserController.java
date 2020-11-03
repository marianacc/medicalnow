package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.RegistryBl;
import com.ucb.medicalnow.BL.UserBl;
import com.ucb.medicalnow.Model.NewUserModel;
import com.ucb.medicalnow.Model.UserAvatarModel;
import com.ucb.medicalnow.Model.UserConfigurationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private RegistryBl registryBl;
    private UserBl userBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public UserController (RegistryBl registryBl, UserBl userBl) {
        this.registryBl = registryBl;
        this.userBl = userBl;
    }

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

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<UserAvatarModel>> returnUserNameByPatientId (@RequestHeader("Authorization") String authorization,
                                                                                 @PathVariable("userId") Integer userId){

        //Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if(!"AUTHN".equals(decodedJWT.getClaim("type").asString())){
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        return new ResponseEntity<>(this.userBl.returnUserNameByUserId(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "config/{patientId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<UserConfigurationModel>> returnUserConfigurationByUserId(@RequestHeader("Authorization") String authorization,
                                                                                             @PathVariable("patientId") Integer patientId){

        //Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if(!"AUTHN".equals(decodedJWT.getClaim("type").asString())){
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        return new ResponseEntity<>(this.userBl.returnUserConfigurationByUserId(patientId), HttpStatus.OK);
    }

}
