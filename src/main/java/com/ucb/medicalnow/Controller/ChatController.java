package com.ucb.medicalnow.Controller;

import com.ucb.medicalnow.BL.ChatBl;
import com.ucb.medicalnow.BL.SecurityBl;
import com.ucb.medicalnow.Interfaces.ImageRepository;
import com.ucb.medicalnow.Model.ImageModel;
import com.ucb.medicalnow.Model.ImageModelTry;
import com.ucb.medicalnow.Model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("api/v1/chat")
public class ChatController {

    private ChatBl chatBl;
    private SecurityBl securityBl;

    @Autowired
    public ChatController(ChatBl chatBl, SecurityBl securityBl) {
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

    @PostMapping("image/upload")
    public ResponseEntity<Map<String, String>> uploadImage(@RequestHeader("Authorization") String authorization,
                                                           @RequestParam("imageFile") MultipartFile file) throws IOException {
        securityBl.validateToken(authorization);

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Boolean resourceResponse = chatBl.addImage(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        Map<String, String> response = new HashMap<>();
        if (resourceResponse){
            response.put("response", "Resource added succesfully");
        } else {
            response.put("response", "Resource not added");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = {"/get/{imageName}" })
    public ResponseEntity<ImageModelTry> getImage(@RequestHeader("Authorization") String authorization,
                                                  @PathVariable("imageName") String imageName){

        securityBl.validateToken(authorization);
        return new ResponseEntity<>(this.chatBl.getImage(imageName), HttpStatus.OK);
    }
}
