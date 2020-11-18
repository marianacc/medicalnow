package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.ConsultBl;
import com.ucb.medicalnow.BL.ChatBl;
import com.ucb.medicalnow.BL.MedicalHistoryBl;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ChatBl chatBl;
    private SecurityBl securityBl;

    @Autowired
    public ChatController(MedicalHistoryBl medicalHistoryBl, ConsultBl consultBl, ChatBl chatBl, SecurityBl securityBl) {
        this.medicalHistoryBl = medicalHistoryBl;
        this.consultBl = consultBl;
        this.chatBl = chatBl;
        this.securityBl = securityBl;
    }

    @RequestMapping(
            value = "patient/{consultId}/messages",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnChatForPatient (@RequestHeader("Authorization") String authorization,
                                                                  @PathVariable("consultId") Integer consultId) {

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.chatBl.returnPatientChat(consultId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "doctor/{consultId}/messages",
            method = RequestMethod.GET,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> returnChatForDoctor (@RequestHeader("Authorization") String authorization,
                                                                 @PathVariable("consultId") Integer consultId) {
        securityBl.validateToken(authorization);

        return new ResponseEntity<>(this.chatBl.returnDoctorConversationByConsultId(consultId), HttpStatus.OK);
    }

    @RequestMapping(
            value = "send/message/{userId}",
            method = RequestMethod.POST,
            produces =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> addMessage(@RequestHeader("Authorization") String authorization,
                           @PathVariable("userId") Integer userId,
                           @RequestBody MessageModel messageModel) {

        securityBl.validateToken(authorization);

        Map<String, String> response = new HashMap<>();
        Boolean conversationResponse = chatBl.addMessageToConversation(messageModel.getConsultId(), messageModel.getMessage(), userId);
        if (conversationResponse){
            response.put("response", "The message was added succesfully");
        } else {
            response.put("response", "The message wasn't added");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
