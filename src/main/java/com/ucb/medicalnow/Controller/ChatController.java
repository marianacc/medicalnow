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

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("image/upload")
    public ResponseEntity.BodyBuilder uplaodImage(@RequestHeader("Authorization") String authorization,
                                                  @RequestParam("imageFile") MultipartFile file) throws IOException {
        securityBl.validateToken(authorization);
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()));
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@RequestHeader("Authorization") String authorization,
                               @PathVariable("imageName") String imageName) throws IOException {

        securityBl.validateToken(authorization);
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
