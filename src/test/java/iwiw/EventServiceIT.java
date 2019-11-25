package iwiw;

import iwiw.model.Event;
import iwiw.model.Message;
import iwiw.model.User;
import iwiw.repository.EventRepository;
import iwiw.repository.UserRepository;
import iwiw.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

    @Autowired
    private UserRepository userRepository;

    @Test
    public void changeEventNameTest(){
        Event event = Event.builder().id(1).name("oldName").build();
        event = eventRepository.save(event);
        eventService.changeEventName(event, "newName");
        assertThat(event.getName(), equalTo("newName"));

    }

    @Test
    public void sendMessageToParticipatingUsersTest(){
        //ARRANGE
        User creator = User.builder().name("creator").userName("creator").password("creator").id(3).build();
        User user1 = User.builder().name("teszt1").userName("teszt1").password("teszt1").id(1).build();
        User user2 = User.builder().name("teszt2").userName("teszt2").password("teszt2").id(2).build();
        Event testEvent = Event.builder().name("testEventName").date(new Date()).id(1).build();

        creator.addCreatedEvent(testEvent);

        creator = userRepository.save(creator);
        testEvent = eventRepository.save(testEvent);

        user1 = userRepository.save(user1);
        user2 = userRepository.save(user2);

        user1.addParticipatedEvent(testEvent, "");
        user2.addParticipatedEvent(testEvent, "");

        //ACT
        eventService.sendMessageToParticipatingUsers(testEvent, "subject", "body");

        //ASSERT
        assertThat(creator.getSentMessages().size(), equalTo(2));
        assertThat(user1.getReceivedMessages().size(), equalTo(1));
        assertThat(user2.getReceivedMessages().size(), equalTo(1));
        assertThat(creator.getSentMessages().contains(user1.getReceivedMessages().toArray()[0]), is(true));
        assertThat(creator.getSentMessages().contains(user2.getReceivedMessages().toArray()[0]), is(true));

    }
}
