package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.Model.ConsultsDoctorModel;
import com.ucb.medicalnow.Model.ConsultsPatientModel;
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
@RequestMapping("api/v1/consults")
public class ConsultController {

    private ConsultBl consultBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public ConsultController(ConsultBl consultBl) {
        this.consultBl = consultBl;
    }

    @RequestMapping(
            value = "patient/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ConsultsPatientModel>> returnAllConsultsByPatient (@RequestHeader("Authorization") String authorization,
                                                                                @PathVariable("userId") Integer userId) {
        // *********
        // Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if(!"AUTHN".equals(decodedJWT.getClaim("type").asString())){
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        // *********

        return new ResponseEntity<>(this.consultBl.returnAllConsultsByPatientId(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<ConsultsDoctorModel>> returnAllConsultsByDoctor (@RequestHeader("Authorization") String authorization,
                                                                                     @PathVariable("userId") Integer userId) {

        // *********
        // Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if(!"AUTHN".equals(decodedJWT.getClaim("type").asString())){
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        // *********

        return new ResponseEntity<>(this.consultBl.returnAllConsultsByDoctorId(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "discharge/{consultId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> dischargeUser (@RequestHeader("Authorization") String authorization,
                                                              @PathVariable("consultId") Integer consultId) {
        // ********
        // Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if(!"AUTHN".equals(decodedJWT.getClaim("type").asString())){
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        // ********

        Map<String, String> response = new HashMap();
        Boolean consultResponse = consultBl.dischargeUserByConsultId(consultId);
        if (consultResponse){
            response.put("message", "Patient discharged succesfully");
        } else {
            response.put("message", "Patient not discharged");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
