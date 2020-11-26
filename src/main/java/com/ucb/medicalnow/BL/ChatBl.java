package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.*;
import com.ucb.medicalnow.Interfaces.ImageRepository;
import com.ucb.medicalnow.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ChatBl {

    @Autowired
    ImageRepository imageRepository;

    private ChatDao chatDao;
    private DoctorDao doctorDao;
    private PatientDao patientDao;
    private UserDao userDao;

    @Autowired
    public ChatBl(ChatDao chatDao, DoctorDao doctorDao, PatientDao patientDao, UserDao userDao) {
        this.chatDao = chatDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
        this.userDao = userDao;
    }

    public Map<String, Object> returnPatientChat (int consultId){
        Map <String, Object> response = new HashMap<>();
        ArrayList<ChatModel> chat = setRoleByUser(consultId);
        if(chat != null){
            DoctorNameModel doctorName = doctorDao.returnDoctorSpecialtyNameByConsult(consultId);
            response.put("content", chat);
            response.put("doctorInfo", doctorName);
        }
        return response;
    }

    public Map<String, Object> returnDoctorConversationByConsultId (int consultId){
        Map <String, Object> response = new HashMap<>();
        ArrayList<ChatModel> chat = setRoleByUser(consultId);
        if(chat != null){
            PatientNameModel patientName = patientDao.returnPatientNameByConsult(consultId);
            response.put("content", chat);
            response.put("patientInfo", patientName);
        }
        return response;
    }

    public ArrayList<ChatModel> setRoleByUser(int consultId){
        ArrayList<ChatModel> chat = chatDao.returnChatByConsult(consultId);
        for (int i = 0; i<chat.size(); i++){
            int roleId = userDao.findRoleIdByUserId(chat.get(i).getRoleId());
            chat.get(i).setRoleId(roleId);
        }
        return chat;
    }

    public Boolean addMessageToConversation(int consultId, String message, int userId){
        Boolean conversationResponse = null;
        Integer result = chatDao.addMessageToChat(consultId, message, userId);
        if (result > 0){
            conversationResponse = true;
        } else {
            conversationResponse = false;
        }
        return conversationResponse;
    }

/*    public Boolean addImage(String originalFileName, String contentType, byte[] bytes){
        byte[] imageCompressed = compressBytes(bytes);

        // Forma 1
        ImageModel img = new ImageModel(originalFileName, contentType, imageCompressed);
        imageRepository.save(img);

        // Forma 2
        Integer addResourceResponse = chatDao.addImage(originalFileName, contentType, imageCompressed);
        Boolean registryUpdated = null;
        if(addResourceResponse>1){
            registryUpdated = true;
        } else {
            registryUpdated = false;
        }
        return registryUpdated;
    }

    public ImageModelTry getImage(String imageName){

        // Forma 1
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;

        // Forma 2
        /*ImageModelTry imageModelTry = chatDao.findImageByName(imageName);
        imageModelTry.setPic_byte(decompressBytes(imageModelTry.getPic_byte()));
        return imageModelTry;
    }*/

  /*  // compress the image bytes before storing it in the database
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
    }*/
}
