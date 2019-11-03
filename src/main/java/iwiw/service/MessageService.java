package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.SentMessageRepository;

import java.util.Date;

public class MessageService {

    SentMessageRepository sentMessageRepository;
    MessageRepository messageRepository;

    public void sendMessage(User fromUser,User toUser,String subject,String body){
        Message message = new Message(subject,new Date(System.currentTimeMillis()),body);
        messageRepository.add(message);
        sentMessageRepository.add(message.getId(),fromUser.getId(),toUser.getId());
    }

}
