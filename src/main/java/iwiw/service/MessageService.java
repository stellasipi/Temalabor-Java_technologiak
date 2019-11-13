package iwiw.service;

import iwiw.model.Message;
import iwiw.model.SentMessage;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.SentMessageRepository;
import iwiw.repository.TagRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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
    public void sendMessage(User fromUser,User toUser,String subject,String body){
        Message message = Message.builder().sender(fromUser).addresseeUser(toUser).body(body)
                    .sentDate(new Date(System.currentTimeMillis())).subject(subject).build();
        userRepository.findById(fromUser.getId()).get().addMessage(message);
        messageRepository.save(message); //Új levél mentése
        userRepository.save(fromUser); //Meglévő felhasználó updatelése - új küldött levél felvétele miatt
    }

    //Egy user postaládájának kilistázása
    @Transactional
    public ArrayList<Message> listUsersInbox(User user){
        return messageRepository.findMessagesByAddressee(user);
    }

    @Transactional
    public void addTagToMessage(Tag tag, Message message){

        messageRepository.findById(message.getId()).get().addTag(tag);
        tagRepository.save(tag);
        messageRepository.save(message);
    }

    @Transactional
    public void removeTagFromMessage(Tag tag, Message message){

        messageRepository.findById(message.getId()).get().removeTag(tag); //Egyelőre nincs kezelve ha nincs olyan tag rajta, de lehet nem is kell
        tagRepository.save(tag);
        messageRepository.save(message);
    }



}
