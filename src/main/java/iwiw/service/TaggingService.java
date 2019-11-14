package iwiw.service;

//import iwiw.repository.MarkedRepository;

import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.model.User;
import iwiw.repository.MessageRepository;
import iwiw.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
public class TaggingService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TagRepository tagRepository;

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

    @Transactional
    public ArrayList<Message> listUsersMessagesWithTag(User user, Tag tag){

        return messageRepository.listUsersMessagesWithTag(user, tag);
    }

}
