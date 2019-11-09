package iwiw.service;

import iwiw.model.Event;
import iwiw.model.Place;
import iwiw.model.User;
import iwiw.repository.EventRepository;

import java.util.Date;

public class EventService {

    private EventRepository eventRepository;

    public void changeEventName(Event event, String name){

        eventRepository.findById(event.getId()).get().setName(name);
    }

    public void changeEventDate(Event event, Date date){
        eventRepository.findById(event.getId()).get().setDate(date);
    }

    public void changeEventPlace(Event event, Place place){
        eventRepository.findById(event.getId()).get().setPlace(place);
    }

    

    //stb,stb...
}
