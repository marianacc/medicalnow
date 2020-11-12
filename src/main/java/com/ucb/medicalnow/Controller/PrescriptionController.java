package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.Model.LaboratoryOrderModel;
import com.ucb.medicalnow.Model.PrescriptionDetailModel;
import com.ucb.medicalnow.Model.PrescriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/prescription")
public class PrescriptionController {

    private PrescriptionBl prescriptionBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public PrescriptionController (PrescriptionBl prescriptionBl) { this.prescriptionBl = prescriptionBl; }

    @RequestMapping(
            value = "{userId}",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<PrescriptionModel>> returnAllPrescriptionsByUserId (@RequestHeader("Authorization") String authorization,
                                                                                        @PathVariable("userId") Integer userId){
        // *********
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
        // *********
        return new ResponseEntity<>(this.prescriptionBl.returnAllPrescriptionsByUserId(userId), HttpStatus.OK);
    }

    /*
    @RequestMapping(
            value="{prescriptionId}/detail",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<PrescriptionDetailModel>> returnPrescriptionDetailByPresctiptionId (@RequestHeader("Authorization") String authorization,
                                                                                                        @PathVariable("prescriptionId") Integer prescriptionId){
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
        return new ResponseEntity<>(this.prescriptionBl.returnPrescriptionDetailByPresctiptionId(prescriptionId), HttpStatus.OK);
    }*/
}
