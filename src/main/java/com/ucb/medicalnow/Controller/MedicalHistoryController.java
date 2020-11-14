package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.Model.MedicalDataModel;
import com.ucb.medicalnow.Model.MedicalHistoryDetailModel;
import com.ucb.medicalnow.Model.MedicalHistoryListModel;
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
@RequestMapping("api/v1/medical_history")
public class MedicalHistoryController {

    private MedicalHistoryBl medicalHistoryBl;

    @Autowired
    public MedicalHistoryController(MedicalHistoryBl medicalHistoryBl) {
        this.medicalHistoryBl = medicalHistoryBl;
    }

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @RequestMapping(
            value = "list/all/{userId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<MedicalHistoryListModel>> returnAllMedicalHistory(@RequestHeader("Authorization") String authorization,
                                                                                              @PathVariable("userId") Integer userId){

        //********
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
        //********

        return new ResponseEntity<>(this.medicalHistoryBl.returnAllMedicalHistory(userId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "detail/{medicalHistoryId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnMedicalHistoryDetail(@RequestHeader("Authorization") String authorization,
                                                                                @PathVariable("medicalHistoryId") Integer medicalHistoryId){

        //********
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
        //********

        return new ResponseEntity<>(this.medicalHistoryBl.returnMedicalHistoryDetail(medicalHistoryId), HttpStatus.OK);
    }
}
