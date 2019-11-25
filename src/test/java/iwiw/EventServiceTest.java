package iwiw;

import iwiw.model.Event;
import iwiw.repository.EventRepository;
import iwiw.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {

    @InjectMocks
    EventService eventService;

    @Mock
    EventRepository eventRepository;

    @Test
    public void changeEventNameTest(){

        Event event = Event.builder().id(1).name("oldName").build();

        when(eventRepository.findById(1)).thenReturn(Optional.of(event));

        eventService.changeEventName(event, "newName");


        assertThat(event.getName(), equalTo("newName"));

    }
}
