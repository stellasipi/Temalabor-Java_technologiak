package iwiw.service;

import iwiw.model.Event;
import iwiw.model.Place;
import iwiw.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

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

    

    //stb,stb...
}
