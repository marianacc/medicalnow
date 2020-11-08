package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConversationDao;
import com.ucb.medicalnow.DAO.SpecialtyDao;
import com.ucb.medicalnow.Model.ConversationModel;
import com.ucb.medicalnow.Model.DoctorSpecialtyModel;
import com.ucb.medicalnow.Model.DoctorSpecialtyNameModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConversationBl {

    private ConversationDao conversationDao;
    private SpecialtyDao specialtyDao;

    @Autowired
    public ConversationBl (ConversationDao conversationDao, SpecialtyDao specialtyDao) {
        this.conversationDao = conversationDao;
        this.specialtyDao = specialtyDao;
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

    public Map<String, Object> returnConversationByConsultId (int consultId){
        ArrayList<ConversationModel> conversation = conversationDao.returnConversationByConsultId(consultId);
        DoctorSpecialtyNameModel doctorSpecialtyName = specialtyDao.returnDoctorAndSpecialtyNameByConsultId(consultId);
        Map <String, Object> chat = new HashMap<>();
        String name = doctorSpecialtyName.getFirstName()+" "+doctorSpecialtyName.getFirstSurname()+" "+doctorSpecialtyName.getSecondSurname();
        chat.put("content", conversation);
        chat.put("doctor", doctorSpecialtyName.getFirstName());
        chat.put("specialty", doctorSpecialtyName.getSpecialtyName());
        return chat;
    }
}
