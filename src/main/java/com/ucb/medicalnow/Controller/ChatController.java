package com.ucb.medicalnow.Controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.ConversationBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.Model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/chat")
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
            value = "{consultId}/messages",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> getChat (@RequestHeader("Authorization") String authorization,
                                                        @PathVariable("consultId") Integer consultId) {
        //Decodificando el token
        String tokenJwt = authorization.substring(7);
        DecodedJWT decodedJWT = JWT.decode(tokenJwt);
        //Validando si el token es bueno y de autenticación
        if (!"AUTHN".equals(decodedJWT.getClaim("type").asString())) {
            throw new RuntimeException("El token proporcionado no es un token de autenticación");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretJwt);
        JWTVerifier verifier = JWT.require(algorithm).withIssuer("Medicalnow").build();
        verifier.verify(tokenJwt);
        return new ResponseEntity<>(this.conversationBl.returnConversationByConsultId(consultId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "send/message/patient/{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addMessageFromPatient (@RequestHeader("Authorization") String authorization,
                                                                      @RequestBody MessageModel messageModel,
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

        Map<String, Object> response = new HashMap<>();
        // Verifica si es que existe una historia medica con el cliente o no
        Map<String, Object> medicalHistoryResult = medicalHistoryBl.medicalHistoryExists(messageModel.getDoctorSpecialtyId(), userId);
        if (medicalHistoryResult.get("exists").equals(true)){
            int medicalHistoryId = Integer.parseInt(medicalHistoryResult.get("id").toString());
            // Verifica si existe una consulta abierta con el doctor
            Map<String, Object> consultResult = consultBl.consultExists(medicalHistoryId);
            if(consultResult.get("exists").equals(true)){
                int consultId = Integer.parseInt(consultResult.get("id").toString());
                // Añadir el mensaje
                Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, messageModel.getMessage(), userId);
                if (conversationResponse){
                    response.put("response", "The message was added succesfully");
                } else {
                    response.put("response", "The message wasn't added succesfully");
                }
            } else {
                // Crear una nueva consulta y agregar el mensaje
                Boolean consultResponse = consultBl.createNewConsult(medicalHistoryId);
                int consultId = Integer.parseInt(consultBl.returnConsultId(medicalHistoryId).toString());
                if(consultResponse){
                    Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, messageModel.getMessage(), userId);
                    if (conversationResponse){
                        response.put("response", "The message was added succesfully");
                    } else {
                        response.put("response", "The message wasn't added succesfully");
                    }
                } else {
                    response.put("response", "Consult not created");
                }
            }
        } else {
            // Crear una historia medica
            Boolean medicalHistoryResponse = medicalHistoryBl.createMedicalHistory(userId, messageModel.getDoctorSpecialtyId());
            if (medicalHistoryResponse){
                int medicalHistoryId = Integer.parseInt(medicalHistoryBl.returnMedicalHistoryId(messageModel.getDoctorSpecialtyId(), userId).toString());
                // Crear una consulta
                Boolean consultResponse = consultBl.createNewConsult(medicalHistoryId);
                int consultId = Integer.parseInt(consultBl.returnConsultId(medicalHistoryId).toString());
                if(consultResponse){
                    // Agregar el mensaje
                    Boolean conversationResponse = conversationBl.addMessageToConversation(consultId, messageModel.getMessage(), userId);
                    if (conversationResponse){
                        response.put("response", "The message wasn added succesfully");
                    } else {
                        response.put("response", "The message wasn't added succesfully");
                    }
                } else {
                    response.put("response", "Consult not created");
                }
            } else {
                response.put("response", "Medical history not created");
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(
            value = "send/message/doctor/{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addMessageFromDoctor (@RequestHeader("Authorization") String authorization,
                                                                     @RequestBody MessageModel messageModel,
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

        Map<String, Object> response = new HashMap<>();
        Boolean conversationResponse = conversationBl.addMessageToConversation(messageModel.getConsultId(), messageModel.getMessage(), userId);
        if (conversationResponse){
            response.put("response", "The message was added succesfully");
        } else {
            response.put("response", "The message wasn't added succesfully");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
