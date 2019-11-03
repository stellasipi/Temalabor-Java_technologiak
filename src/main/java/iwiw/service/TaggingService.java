package iwiw.service;

import iwiw.model.Marked;
import iwiw.model.Message;
import iwiw.model.Tag;
import iwiw.repository.MarkedRepository;
import iwiw.repository.TagRepository;

import java.util.List;

public class TaggingService {

    MarkedRepository markedRepository;

    public void addNewTagtoAMessage(Tag tag, Message message){
        Marked marked=new Marked(message.getId(),tag.getId());
        markedRepository.save(marked);
    }

    public void removeATagFromAMessage(Tag tag, Message message){
        List<Marked> messageTags=markedRepository.listByMessageId(message.getId());
        for(Marked marked:messageTags){
            if(marked.getTagId().equals(tag.getId())){
                markedRepository.delete(marked);
            }
        }
    }

}