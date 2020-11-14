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
public class ConversationBl {

    private ConversationDao conversationDao;
    private DoctorDao doctorDao;
    private PatientDao patientDao;
    private UserDao userDao;

    @Autowired
    public ConversationBl (ConversationDao conversationDao, DoctorDao doctorDao, PatientDao patientDao, UserDao userDao) {
        this.conversationDao = conversationDao;
        this.doctorDao = doctorDao;
        this.patientDao = patientDao;
        this.userDao = userDao;
    }

    public Boolean addMessageToConversation (int consultId, String message, int userId){
        Boolean conversationResponse = null;
        Integer result = conversationDao.addMessageToConversation(consultId, message, userId);
        if (result > 0){
            conversationResponse = true;
        } else {
            conversationResponse = false;
        }
        return conversationResponse;
    }

    public Map<String, Object> returnPatientConversationByConsultId (int consultId){
        ArrayList<ChatModel> conversation = conversationDao.returnConversationByConsultId(consultId);
        for (int i = 0; i<conversation.size(); i++){
            int roleId = userDao.findRoleIdByUserId(conversation.get(i).getRoleId());
            conversation.get(i).setRoleId(roleId);
        }
        DoctorNameModel doctorName = doctorDao.returnDoctorAndSpecialtyNameByConsultId(consultId);
        Map <String, Object> chat = new HashMap<>();
        chat.put("content", conversation);
        chat.put("doctorInfo", doctorName);
        return chat;
    }

    public Map<String, Object> returnDoctorConversationByConsultId (int consultId){
        ArrayList<ChatModel> conversation = conversationDao.returnConversationByConsultId(consultId);
        for (int i = 0; i<conversation.size(); i++){
            int roleId = userDao.findRoleIdByUserId(conversation.get(i).getRoleId());
            conversation.get(i).setRoleId(roleId);
        }
        PatientNameModel patientName = patientDao.returnPatientNameByConsultId(consultId);
        Map <String, Object> chat = new HashMap<>();
        chat.put("content", conversation);
        chat.put("patientInfo", patientName);
        return chat;
    }
}
