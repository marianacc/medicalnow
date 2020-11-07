package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.ConversationBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.DAO.PatientDao;
import com.ucb.medicalnow.Model.ConsultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class ChatController {

    private MedicalHistoryBl medicalHistoryBl;
    private ConsultBl consultBl;
    private ConversationBl conversationBl;

    @Autowired
    public ChatController(MedicalHistoryBl medicalHistoryBl, ConsultBl consultBl, ConversationBl conversationBl) {
        this.medicalHistoryBl = medicalHistoryBl;
        this.consultBl = consultBl;
        this.conversationBl = conversationBl;
    }

    @Value("${medicalnow.security.secretJwt}")
    private String secretJwt;

    @RequestMapping(
            value = "{userId}/chat",
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

        // Verifica si es que existe una historia medica con el cliente o no
        Map<String, Object> medicalHistoryResult = medicalHistoryBl.medicalHistoryExists(consultModel.getDoctorSpecialtyId(), userId);
        if (medicalHistoryResult.get("exists").equals(true)){
            int medicalHistoryId = Integer.parseInt(medicalHistoryResult.get("id").toString());

            // Verifica si existe una consulta abierta con el doctor
            Map<String, Object> consultResult = consultBl.consultExists(medicalHistoryId);
            if(consultResult.get("exists").equals(true)){
                int consultId = Integer.parseInt(consultResult.get("id").toString());

                // Añadir el mensaje
                Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, consultModel.getMessage(), userId);
                if (conversationResponse){
                    System.out.print("Mensaje añadido");
                } else {
                    System.out.print("Mensaje no añadido");
                }
            } else {
                // Crear una nueva consulta y agregar el mensaje

                Boolean consultResponse = consultBl.createNewConsult(medicalHistoryId);
                int consultId = Integer.parseInt(consultBl.returnConsultId(medicalHistoryId).toString());
                if(consultResponse){
                    Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, consultModel.getMessage(), userId);
                    if (conversationResponse){
                        System.out.print("Mensaje añadido");
                    } else {
                        System.out.print("Mensaje no añadido");
                    }
                } else {
                    System.out.print("Consulta no creada");
                }
            }
        } else {

            // Crear una historia medica
            Boolean medicalHistoryResponse = medicalHistoryBl.createMedicalHistory(userId, consultModel.getDoctorSpecialtyId());
            if (medicalHistoryResponse){
                int medicalHistoryId = Integer.parseInt(medicalHistoryBl.returnMedicalHistoryId(consultModel.getDoctorSpecialtyId(), userId).toString());

                // Crear una consulta
                Boolean consultResponse = consultBl.createNewConsult(medicalHistoryId);
                int consultId = Integer.parseInt(consultBl.returnConsultId(medicalHistoryId).toString());
                if(consultResponse){

                    // Agregar el mensaje
                    Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, consultModel.getMessage(), userId);
                    if (conversationResponse){
                        System.out.print("Mensaje añadido");
                    } else {
                        System.out.print("Mensaje no añadido");
                    }
                } else {
                    System.out.print("Consulta no creada");
                }

            } else {
                System.out.print("Historia medica no añadida");
            }
        }
    }
}
