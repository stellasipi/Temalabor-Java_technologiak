package iwiw.service;

import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagRepository tagRepository;

    @Transactional
    public void sendMessage(User fromUser,User toUser,/*String subject,String body*/Message message){
        Message messageToSend = Message.builder()
                                .sender(fromUser)
                                .addressee(toUser)
                                .sentDate(new Date(System.currentTimeMillis()))
                                .subject(message.getSubject())
                                .body(message.getBody())
                                .build();
        userRepository.findById(fromUser.getId()).get().addOutgoingMessage(messageToSend); //feladó beállítása
        userRepository.findById(toUser.getId()).get().addIncomingMessage(messageToSend); //címzett beállítása
        messageRepository.save(messageToSend); //Új levél mentése
    }

    @Transactional
    public void sendMessageToAllFriends(User sender, Message message){

        User userSender = userRepository.findById(sender.getId()).get();
        for(Iterator<User> iterator=userSender.getFriends().iterator();iterator.hasNext();){
            User friend=iterator.next();
            User addressee = userRepository.findById(friend.getId()).get();
            sendMessage(sender, addressee, message);

        }
    }

    //Egy user postaládájának kilistázása
    @Transactional
    public List<Message> listUsersInbox(User user){
        return messageRepository.findMessagesByAddressee(user);
    }
}
