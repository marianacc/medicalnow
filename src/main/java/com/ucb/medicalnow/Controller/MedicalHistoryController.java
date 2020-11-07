package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.MedicalHistoryBl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/chat")
public class MedicalHistoryController {

    private MedicalHistoryBl medicalHistoryBl;

    @Autowired
    public MedicalHistoryController(MedicalHistoryBl medicalHistoryBl) {
        this.medicalHistoryBl = medicalHistoryBl;
    }

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

   /*@RequestMapping(
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


        consultBl.addConsultToMedicalHistory(consultModel.getDoctorSpecialtyId(), consultModel.getMessage(),
                consultModel.getImage(), userId);


    }*/
}
