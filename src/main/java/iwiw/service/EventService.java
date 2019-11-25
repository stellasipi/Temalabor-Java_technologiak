package iwiw.service;

import iwiw.model.Event;
import iwiw.model.Message;
import iwiw.model.Place;
import iwiw.model.User;
import iwiw.repository.EventRepository;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Transactional
    public void changeEventName(Event event, String name){

        eventRepository.findById(event.getId()).get().setName(name);
        eventRepository.save(event);
    }

    @Transactional
    public void changeEventDate(Event event, Date date){
        eventRepository.findById(event.getId()).get().setDate(date);
        eventRepository.save(event);

    }

    @Transactional
    public void changeEventPlace(Event event, Place place){
        eventRepository.findById(event.getId()).get().setPlace(place);
        eventRepository.save(event);
    }

    @Transactional
    public void sendMessageToParticipatingUsers(Event testEvent, String subject, String body) {
            testEvent.getParticipatingUsers().forEach(userEvent -> {
                User participant = userRepository.findById(userEvent.getUser().getId()).get();
                Message message = Message.builder().subject(subject).body(body).sentDate(new Date()).build();
                testEvent.getCreatorUser().addOutgoingMessage(message);
                participant.addIncomingMessage(message);
                messageRepository.save(message);
            });

    }

}
