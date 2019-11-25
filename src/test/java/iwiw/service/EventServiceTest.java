package iwiw.service;

import iwiw.model.Event;
import iwiw.model.Message;
import iwiw.model.Place;
import iwiw.model.User;
import iwiw.repository.EventRepository;
import iwiw.repository.MessageRepository;
import iwiw.repository.UserRepository;
import iwiw.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    MessageRepository messageRepository;

    @Test
    public void changeEventNameTest(){
        Event event = Event.builder().id(1).name("oldName").build();
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        eventService.changeEventName(event, "newName");
        assertThat(event.getName(), equalTo("newName"));

    }

    @Test
    public void changeEventDateTest(){
        Date oldDate = new Date(System.currentTimeMillis() - 24*60*60*1000);
        Date newDate = new Date(System.currentTimeMillis() + 24*60*60*1000);
        Event event = Event.builder().id(1).date(oldDate).build();
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        eventService.changeEventDate(event, newDate);
        assertThat(event.getDate(), equalTo(newDate));
    }



    @Test
    public void sendMessageToParticipatingUsersTest(){
        //ARRANGE
        User creator = User.builder().name("creator").userName("creator").password("creator").id(3).build();
        User user1 = User.builder().name("teszt1").userName("teszt1").password("teszt1").id(1).build();
        User user2 = User.builder().name("teszt2").userName("teszt2").password("teszt2").id(2).build();
        Event testEvent = Event.builder().name("testEventName").date(new Date()).id(1).build();

        creator.addCreatedEvent(testEvent);
        user1.addParticipatedEvent(testEvent, "");
        user2.addParticipatedEvent(testEvent, "");

        when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        when(userRepository.findById(2)).thenReturn(Optional.of(user2));

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
