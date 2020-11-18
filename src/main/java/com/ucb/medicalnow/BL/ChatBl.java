package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.*;
import com.ucb.medicalnow.Model.ChatModel;
import com.ucb.medicalnow.Model.DoctorNameModel;
import com.ucb.medicalnow.Model.PatientNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatBl {

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
            DoctorNameModel doctorName = doctorDao.returnDoctorAndSpecialtyNameByConsultId(consultId);
            response.put("content", chat);
            response.put("doctorInfo", doctorName);
        }
        return response;
    }

    public Map<String, Object> returnDoctorConversationByConsultId (int consultId){
        Map <String, Object> response = new HashMap<>();
        ArrayList<ChatModel> chat = setRoleByUser(consultId);
        if(chat != null){
            PatientNameModel patientName = patientDao.returnPatientNameByConsultId(consultId);
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
}
