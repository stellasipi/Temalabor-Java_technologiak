package iwiw.service;

import iwiw.model.Message;
import iwiw.model.SentMessage;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.SentMessageRepository;

import java.util.Date;

public class MessageService {

    SentMessageRepository sentMessageRepository;
    MessageRepository messageRepository;

    public void sendMessage(User fromUser,User toUser,String subject,String body){
        Message message = new Message(subject,new Date(System.currentTimeMillis()),body);
        messageRepository.save(message);
        SentMessage sentMessage=new SentMessage(toUser.getId(),fromUser.getId(),message.getId());
        sentMessageRepository.save(sentMessage);
    }

}
