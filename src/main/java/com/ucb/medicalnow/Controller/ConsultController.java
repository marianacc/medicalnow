package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.Model.ConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/consult")
public class ConsultController {

    private ConsultBl consultBl;

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @Autowired
    public ConsultController(ConsultBl consultBl) {
        this.consultBl = consultBl;
    }

    @RequestMapping(
            value="{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public void addToMedicalHistory (@RequestHeader("Authorization") String authorization,
                                                                    @RequestBody ConsultModel consultModel,
                                                                    @PathVariable("userId") Integer userId) {
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


        Boolean consultResponse = consultBl.addConsultToMedicalHistory(consultModel.getDoctorSpecialtyId(), consultModel.getMessage(),
                consultModel.getImage(), userId);

        if(consultResponse){
            System.out.print("hola");
        }

    }
}
