package iwiw;

import iwiw.model.Event;
import iwiw.repository.EventRepository;
import iwiw.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
public class EventServiceIT {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    EventService eventService;

    @Test
    public void changeEventNameTest(){

        Event event = Event.builder().id(1).name("oldName").build();

        eventRepository.save(event);
        eventService.changeEventName(event, "newName");



    }
}
