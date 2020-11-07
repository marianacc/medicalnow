package com.ucb.medicalnow.BL;

import com.ucb.medicalnow.DAO.ConversationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationBl {

    private ConversationDao conversationDao;

    @Autowired
    public ConversationBl (ConversationDao conversationDao) { this.conversationDao = conversationDao;}

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
}
