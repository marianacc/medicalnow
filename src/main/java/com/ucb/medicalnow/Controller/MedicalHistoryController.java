package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.BL.PrescriptionBl;
import com.ucb.medicalnow.Model.PatientConsultModel;
import com.ucb.medicalnow.Model.PrescriptionModel;
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
@RequestMapping("api/v1/consult")
public class MedicalHistoryController {

    private MedicalHistoryBl medicalHistoryBl;
    private ConsultBl consultBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public MedicalHistoryController(MedicalHistoryBl medicalHistoryBl, ConsultBl consultBl) {
        this.medicalHistoryBl = medicalHistoryBl;
        this.consultBl = consultBl;
    }

    @RequestMapping(
            value="{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public void addToMedicalHistory (@RequestHeader("Authorization") String authorization,
                                                                    @RequestBody PatientConsultModel patientConsultModel,
                                                                    @PathVariable("userId") Integer userId) throws ParseException {
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

        Boolean medicalHistoryUpdated = medicalHistoryBl.createMedicalHistory(userId);
        Boolean consultUpdated = consultBl.addConsultToMedicalHistory(patientConsultModel, userId);

        if (medicalHistoryUpdated && consultUpdated){
            System.out.print("ya esta");
        } else {
            System.out.print("error");
        }
    }
}
