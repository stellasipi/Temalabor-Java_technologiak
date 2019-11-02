package iwiw.service;

import iwiw.model.Event;
import iwiw.repository.EventRepository;

public class EventService {

    private EventRepository eventRepository;

    public void changeEventName(Event event, String name){

        eventRepository.findById(event.getId()).setName(name);
    }

    //stb,stb...
}
